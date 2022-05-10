package com.yxy.agent.entity.req;


import java.io.Serializable;

/**
 * @author ：29520
 * @date ：Created in 2021/5/31 20:06
 * @description：
 * @modified By：
 * @version:
 */
public class RequestHeader  implements Serializable {
    /**
     * 渠道
     */
    private String txChnlNo;

    /**
     * 版本号
     */
    private String apiVersion;

    /**
     * 请求时间
     */
    private String msgTime;

    /**
     * 请求流水号
     */
    private String msgSeq;

    private String sid;
    public  String getMsgSeq(){
        if(msgSeq == null){
            return sid;
        }
        return msgSeq;
    }
    public  String getSid(){
        if(sid == null){
            return msgSeq;
        }
        return sid;
    }

    public String getTxChnlNo() {
        return txChnlNo;
    }

    public void setTxChnlNo(String txChnlNo) {
        this.txChnlNo = txChnlNo;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }

    public void setMsgSeq(String msgSeq) {
        this.msgSeq = msgSeq;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
