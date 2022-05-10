package com.yxy.agent.entity;

/**
 * 接收外部参数信息
 *
 * @author yxy
 * @date 2022/05/10
 */
public class ExternalParamInfo {
    private String className;
    private String codeHref;
    private String systemCode;

    public String getClassName() {
        return className;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCodeHref() {
        return codeHref;
    }

    public void setCodeHref(String codeHref) {
        this.codeHref = codeHref;
    }
}
