package com.yxy.agent.utils.yxy;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.yxy.agent.PublicAgentMain;
import javassist.CtClass;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * http工具类
 *
 * @author yxy
 * @date 2022/05/10
 */
public class HttpClientUtils {

    /**
     * 发送post请求
     * @param url
     * @param soapRequestXml
     * @return
     * @throws Exception
     */
    public static String sendPostSoapXml(String url, String soapRequestXml) throws Exception {
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(url);
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(60000)
                .setConnectionRequestTimeout(60000)
                .setConnectTimeout(60000)
                .build();
        httpPost.setConfig(requestConfig);

        CloseableHttpResponse response = null;

        try {
            //设置post请求方式为SOAP+XML
            httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
            StringEntity data = new StringEntity(soapRequestXml, Charset.forName("UTF-8"));
            httpPost.setEntity(data);

            //发送post请求
            response = closeableHttpClient.execute(httpPost);
            if (response == null || response.getStatusLine() == null) {
                throw new RuntimeException("发送POST请求失败，返回结果为空！");
            }

            //返回状态是否200
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                //得到请求结果
                HttpEntity entityRes = response.getEntity();
                if (entityRes != null) {
                    String responsetData = EntityUtils.toString(entityRes, "UTF-8");
                    return responsetData;
                }
            } else {
                throw new Exception("发送POST-SOAP+XML请求出错：" + response);
            }
        } catch (Exception e) {
            throw new Exception("发送POST请求出现异常,"+e.getMessage());
        } finally {
            try {
                // 关闭连接释放资源
                if (response != null) {
                    response.close();
                }
                if (closeableHttpClient != null) {
                    closeableHttpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "";
    }


    /**
     * 发送post请求
     * @param url
     * @param requestJson
     * @return
     * @throws Exception
     */
    public static String sendPostJson(String url, String requestJson) throws Exception {
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(url);
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(60000)
                .setConnectionRequestTimeout(60000)
                .setConnectTimeout(60000)
                .build();
        httpPost.setConfig(requestConfig);

        CloseableHttpResponse response = null;

        try {
            //设置post请求方式为JSON
//            httpPost.addHeader("Authorization", "Basic" + encryptBASE64("username", "password"));
            StringEntity data = new StringEntity(requestJson, "UTF-8");
            data.setContentType("application/json; charset=UTF-8");
            httpPost.setEntity(data);

            //发送post请求
            response = closeableHttpClient.execute(httpPost);
            if (response == null || response.getStatusLine() == null) {
                throw new Exception("发送POST请求失败，返回结果为空！");
            }

            //返回状态是否200
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                //得到请求结果
                HttpEntity entityRes = response.getEntity();
                if (entityRes != null) {
                    String responsetData = EntityUtils.toString(entityRes, "UTF-8");
                    return responsetData;
                }
            } else {
                throw new Exception("发送POST-JSON请求出错：" + response);
            }
        } catch (Exception e) {
            throw new Exception("发送POST请求出现异常,"+e.getMessage());
        } finally {
            try {
                // 关闭连接释放资源
                if (response != null) {
                    response.close();
                }
                if (closeableHttpClient != null) {
                    closeableHttpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "";
    }

    public static Object getOutParam(Object[] inParam, String className, String methodName, String returnClassName){
        String classMethodName = className + "." + methodName;
        try {
            String s1 = JSON.toJSONString(inParam);
            String x = s1.replaceAll("\"", "\\\\\"");
            String requestJson = "{" +
                    "    \"inParam\":\"" + x + "\"," +
                    "    \"methodName\":\"" + classMethodName + "\"," +
                    "    \"systemCode\":\"" + PublicAgentMain.externalParamInfo.getSystemCode() + "\"" +
                    "}";
            System.out.println("yxyagent->returnClassName->" + returnClassName);
            System.out.println("yxyagent->请求数据->" + requestJson);
            String s = sendPostJson(PublicAgentMain.externalParamInfo.getCodeHref(), requestJson);
            System.out.println("yxyagent->返回值->" + s);
            if(s == null || s.trim().equals("")){
                return null;
            }
            Object c = null;
            if("int".equals(returnClassName)){
                System.out.println("yxyagent->returnClassName-1>Integer->" + Integer.valueOf(s));
                return Integer.valueOf(s);
            }else if("double".equals(returnClassName)){
                return Double.valueOf(s);
            }else if("float".equals(returnClassName)){
                return Float.valueOf(s);
            }else if("long".equals(returnClassName)){
                return Long.valueOf(s);
            }else if("boolean".equals(returnClassName)){
                return Boolean.valueOf(s);
            }else if("byte".equals(returnClassName)){
                return Byte.valueOf(s);
            }else if("char".equals(returnClassName)){
                return Character.valueOf(s.charAt(0));
            }else if("short".equals(returnClassName)){
                return Short.valueOf(s);
            }else if(returnClassName.endsWith("[]")){
                c = JSON.parseObject(s, List.class);
            }else {
                c = JSON.parseObject(s, Class.forName(returnClassName));
            }
            return c;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public static String encryptBASE64(String username, String password) {
        byte[] key = (username+":"+password).getBytes();
        return  new String(Base64.encodeBase64(key));
    }
}