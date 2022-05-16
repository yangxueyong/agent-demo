package com.yxy.agent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yxy.agent.entity.ExternalParamInfo;
import javassist.*;

import java.io.IOException;
import java.lang.instrument.Instrumentation;


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

        String className1 = getClassNameString(arg);
        if (className1 == null) return;

        String[] classNames = className1.split(",");

        // 使用 javassist ,在运行时修改 class 字节码，就是 插桩
        ClassPool pool = ClassPool.getDefault();
        instrumentation.addTransformer((loader, className, classBeingRedefined, protectionDomain, classfileBuffer) -> {
            className = className.replaceAll("/",".");
            boolean flag = ckClassName(classNames, className);
            try {
                if(flag) {
                    return getMethodBytes(className, pool);
                }else {
                    return null;
                }
            } catch (NotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (CannotCompileException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * 判断当前类是否需要被重构
     *
     * @param classNames 类名
     * @param className  类名
     * @return boolean
     */
    private static boolean ckClassName(String[] classNames, String className) {
        if(className != null && (className.contains("EnhancerBySpringCGLIB") || className.contains("FastClassBySpringCGLIB")|| className.contains("$"))){
            return false;
        }
        for (String name : classNames) {
            if (className != null && className.startsWith(name)) {
                System.out.println("yxyagent-> className-> " + className);
                return true;
            }
        }
        return false;
    }

    /**
     * 得到类名字符串
     *
     * @param arg 参数
     * @return {@link String}
     */
    private static String getClassNameString(String arg) {
        final String config = arg;
        if(config == null || config.trim().equals("")){
            System.out.println("yxyagent-> 参数为空");
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("yxyagent->" + config);
        try {
            externalParamInfo = mapper.readValue(config, ExternalParamInfo.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String className1 = externalParamInfo.getClassName();
        if(className1 == null || className1.trim().equals("")){
            System.out.println("yxyagent-> 拦截的className为空-> 无法拦截");
            return null;
        }
        try {
            System.out.println("yxyagent-> externalParamInfo->"+mapper.writeValueAsString(externalParamInfo));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return className1;
    }

    /**
     * get方法字节
     *
     * @param className 类名
     * @param pool      池
     * @return {@link byte[]}
     * @throws NotFoundException      没有发现异常
     * @throws IOException            ioexception
     * @throws CannotCompileException 不能编译例外
     */
    private static byte[] getMethodBytes(String className, ClassPool pool) throws NotFoundException, IOException, CannotCompileException {
        CtClass ctClass = null;
        try {
            ctClass = pool.get(className);
        } catch (NotFoundException e) {
            System.out.println("yxyagent->找不到" + e.getMessage());
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
            }
        }
        return ctClass.toBytecode();
    }

    /**
     * 新方法
     *
     * @param className 类名
     * @param oldMethod 老方法
     * @return {@link CtMethod}
     *///复制原有的方法（类似于使用 agent ）
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
                oldMethod.setBody(String.format(voidSource,oldMethodName));
            } else {
                String returnName = oldMethod.getReturnType().getName();
                oldMethod.setBody(String.format(source, returnName,className,oldMethodName,returnName,oldMethodName));
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
            "            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();" +
            "            String returnClassName = \"%s\";" +
            "            String out = com.yxy.agent.utils.yxy.YxyHttpClientUtils.getOutParam($args,\"%s\",\"%s\");" +
            "            System.out.println(\"---yxyagent最终返回-->\" + out);" +
            "            if(out != null){" +
            "               if(\"int\".equals(returnClassName)){\n" +
            "                   return Integer.valueOf(out).intValue();\n" +
            "               }else if(\"double\".equals(returnClassName)){\n" +
            "                   return Double.valueOf(out).doubleValue();\n" +
            "               }else if(\"float\".equals(returnClassName)){\n" +
            "                   return Float.valueOf(out).floatValue();\n" +
            "               }else if(\"long\".equals(returnClassName)){\n" +
            "                   return Long.valueOf(out).longValue();\n" +
            "               }else if(\"boolean\".equals(returnClassName)){\n" +
            "                   return Boolean.valueOf(out).booleanValue();\n" +
            "               }else if(\"byte\".equals(returnClassName)){\n" +
            "                   return Byte.valueOf(out).byteValue();\n" +
            "               }else if(\"char\".equals(returnClassName)){\n" +
            "                   return Character.valueOf(out.charAt(0));\n" +
            "               }else if(\"short\".equals(returnClassName)){\n" +
            "                   return Short.valueOf(out).shortValue();\n" +
            "               }else if(returnClassName.endsWith(\"[]\")){\n" +
            "                   return ($w)mapper.readValue(out, java.util.List.class);\n" +
            "               }else {\n" +
            "                   return ($w)mapper.readValue(out, %s.class);\n" +
            "               }" +
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