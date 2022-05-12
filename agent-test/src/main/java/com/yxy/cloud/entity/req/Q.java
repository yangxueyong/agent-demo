package com.yxy.cloud.entity.req;


import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 公共的请求参数
 *
 * @author 29520
 * @date 2021/05/31
 */
@NoArgsConstructor
public class Q<B> implements Serializable {

    private RequestHeader header;

    private B body;

    private Q(RequestHeader header, B body) {
        this.header = header;
        this.body = body;
    }


    public static <B> Q<B> data(RequestHeader header,B data) {
        return new Q(header, data);
    }

    public RequestHeader getHeader() {
        return header;
    }

    public void setHeader(RequestHeader header) {
        this.header = header;
    }

    public B getBody() {
        return body;
    }

    public void setBody(B body) {
        this.body = body;
    }
}