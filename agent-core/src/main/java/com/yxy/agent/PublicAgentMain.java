package com.yxy.agent;

import com.alibaba.fastjson.JSON;
import com.yxy.agent.entity.ExternalParamInfo;
import javassist.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;


/**
 * 监听所有方法类
 *
 * @author yxy
 * @date 2022/05/10
 */
public class PublicAgentMain {

    //javaagent 入口方法
    public static ExternalParamInfo externalParamInfo = null;

    public static void premain(String arg, Instrumentation instrumentation) {

        System.out.println("yxyagent-> I am here!!!!!");

        final String config = arg;
        if(config == null || config.trim().equals("")){
            System.out.println("yxyagent-> 参数为空");
            return;
        }
        System.out.println("yxyagent->" + config);
        externalParamInfo = JSON.parseObject(config, ExternalParamInfo.class);

        String className1 = externalParamInfo.getClassName();
        if(className1 == null || className1.trim().equals("")){
            System.out.println("yxyagent-> 拦截的className为空-> 无法拦截");
            return;
        }

        String[] classNames = className1.split(",");


        // 使用 javassist ,在运行时修改 class 字节码，就是 插桩
        ClassPool pool = ClassPool.getDefault();
        instrumentation.addTransformer(new ClassFileTransformer() {
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

                className = className.replaceAll("/",".");

                if(className != null && className.contains("EnhancerBySpringCGLIB")){
                    return null;
                }

                boolean flag = false;
                for (String name : classNames) {
                    if (className != null && className.startsWith(name)) {
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    return null;
                }

                try {
                    CtClass ctClass = null;
                    try {
                        ctClass = pool.get(className);
                    } catch (NotFoundException e) {
                        throw new RuntimeException(e);
                    } finally {
                        try {
                            pool.insertClassPath(new LoaderClassPath(Thread.currentThread().getContextClassLoader()));
                            ctClass = pool.get(className);
                        }catch (Exception e){
                            System.out.println("yxyagent->找不到" + e.getMessage());
                            return null;
                        }
                    }
                    System.out.println("ctClass->" + ctClass.getName());
                    // 获得类中的所有方法
                    for (CtMethod declaredMethod : ctClass.getDeclaredMethods()) {
                        System.out.println("declaredMethod->" + declaredMethod.getName());
                        if (!declaredMethod.getReturnType().equals(CtClass.voidType)) {
                            newMethod(className, declaredMethod);
                        }else{
                            System.out.println("进入到else------------->\n\n\n\n");
//                            declaredMethod.insertAfter("System.out.println(\"这是个返回值为空的方法\");");
                        }
                    }
                    return ctClass.toBytecode();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }
        });


    }

    //复制原有的方法（类似于使用 agent ）
    private static CtMethod newMethod(String className,CtMethod oldMethod) {
        CtMethod copy = null;
        try {
            String oldMethodName = oldMethod.getName();
            //1. 将方法进行复制
            copy = CtNewMethod.copy(oldMethod, oldMethod.getDeclaringClass(), null);
            //类似于使用动态代理
            copy.setName(oldMethodName + "$agent");
            //类文件中添加 sayHello$agent 方法
            oldMethod.getDeclaringClass().addMethod(copy);

            //2. 改变原有的方法,将 原有的 sayHello 方法进行重写操作
            if (oldMethod.getReturnType().equals(CtClass.voidType)) {
//                System.out.println(String.format(voidSource,oldMethodName));
                oldMethod.setBody(String.format(voidSource,oldMethodName));
            } else {
//                System.out.println(String.format(source, className,oldMethodName,oldMethod.getReturnType().getName(),oldMethodName));
                oldMethod.setBody(String.format(source, className,oldMethodName,oldMethod.getReturnType().getName(),oldMethodName));
            }
        } catch (CannotCompileException e){
            e.printStackTrace();
        }catch ( NotFoundException e) {
            e.printStackTrace();
        }
        return copy;

    }

    /**
     * 参数的封装
     * $$ ======》 arg1, arg2, arg3
     * $1 ======》 arg1
     * $2 ======》 arg2
     * $3 ======》 arg3
     * $args ======》 Object[]
     */
    //有返回值得方法
    final static String source = "{ long begin = System.currentTimeMillis();\n" +
            "        Object result;\n" +
            "        try {\n" +
            "            Object out = com.yxy.agent.utils.yxy.YxyHttpClientUtils.getOutParam($args,\"%s\",\"%s\",\"%s\");" +
            "            \n" +
            "            String s = com.alibaba.fastjson.JSON.toJSONString(out);" +
            "            System.out.println(\"---yxyagent最终返回1-->\" + s);" +
            "            System.out.println(\"---yxyagent最终返回2-->\" + out);" +
            "            if(out != null){" +
            "               if(out instanceof Integer){\n" +
                    "            return ((Integer) out).intValue();\n" +
                    "        }else if(out instanceof Double){\n" +
                    "            return ((Double) out).doubleValue();\n" +
                    "        }else if(out instanceof Float){\n" +
                    "            return ((Float) out).floatValue();\n" +
                    "        }else if(out instanceof Boolean){\n" +
                    "            return ((Boolean) out).booleanValue();\n" +
                    "        }else if(out instanceof Character){\n" +
                    "            return ((Character) out).charValue();\n" +
                    "        }else if(out instanceof Short){\n" +
                    "            return ((Short) out).shortValue();\n" +
                    "        }else if(out instanceof Byte){\n" +
                    "            return ((Byte) out).byteValue();\n" +
                    "        }else if(out instanceof Long){\n" +
                    "            return ((Long) out).longValue();\n" +
                    "        }" +
            "               return ($w)out;" +
            "            }" +
            "            result = ($w)%s$agent($$);\n" + //s% 将参数传递到下一个方法，然后使用 s% 传递的参数进行替换操作, $w 表示的是在进行return的时候会强制的进行类型转换
            "        } finally {\n" +
            "            long end = System.currentTimeMillis();\n" +
            "            System.out.println(\"yxyagent代理执行时间->\"+(end - begin));\n" +
            "        }\n" +
            "        return ($r) result;}";

    //没有返回值的方法
//    final static String voidSource = "{long begin = System.currentTimeMillis();\n" +
//            "        try {\n" +
//            "            %s$agent($$);\n" +
//            "        } finally {\n" +
//            "            long end = System.currentTimeMillis();\n" +
//            "            System.out.println(\"yxyagent代理执行时间->\"+(end - begin));\n" +
//            "        }}";
    final static String voidSource = "{long begin = System.currentTimeMillis();\n" +
            "        try {\n" +
            "            %s$agent($$);\n" +
            "        } finally {\n" +
            "            long end = System.currentTimeMillis();\n" +
            "            System.out.println(\"yxyagent代理执行时间->\"+(end - begin));\n" +
            "        }}";
}