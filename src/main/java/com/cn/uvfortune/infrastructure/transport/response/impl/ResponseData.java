package com.cn.uvfortune.infrastructure.transport.response.impl;


import com.cn.uvfortune.infrastructure.transport.response.BaseResponseData;
import com.cn.uvfortune.infrastructure.transport.response.OptStatusCode;

/**
 * @author xiaojixiang
 * @version v1.0
 * @description 默认格式消息返回，包含操作状态码、响应提示消息、响应具体内容
 * @date 18-7-6
 */
public class ResponseData<T> extends BaseResponseData<T> {

    /**
     * 操作响应状态码
     */
    private OptStatusCode optStatusCode;

    /**
     * 响应提示消息
     */
    private String message;


    /**
     * 响应具体内容
     *
     * @param data
     */
    @Override
    public ResponseData setDataContext(T data) {
        this.setData(data);
        return this;
    }

    public ResponseData(OptStatusCode optStatusCode, String message) {
        this.optStatusCode = optStatusCode;
        this.message = message;
    }

    /**
     * 返回状态码
     *
     * @return
     */
    public int getOptStatusCode() {
        return optStatusCode.getCode();
    }

    public void setOptStatusCode(OptStatusCode optStatusCode) {
        this.optStatusCode = optStatusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "optStatusCode=" + optStatusCode +
                ", message='" + message + '\'' +
                '}';
    }
}
