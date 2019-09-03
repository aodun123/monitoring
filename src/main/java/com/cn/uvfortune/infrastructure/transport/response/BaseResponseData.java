package com.cn.uvfortune.infrastructure.transport.response;

/**
 * @author xiaojixiang
 * @version v1.0
 * @description 消息响应内容
 * @date 18-7-6
 */
public abstract class BaseResponseData<T> {

    /**
     * 响应内容
     */
    private T data;

    protected void setData(T data) {
        this.data = data;
    }

    /**
     * 响应具体内容
     */
    public abstract BaseResponseData setDataContext(T data);

    public T getData() {
        return data;
    }
}
