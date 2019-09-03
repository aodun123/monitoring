package com.cn.uvfortune.infrastructure.transport.response.impl;


import com.cn.uvfortune.infrastructure.transport.response.OptStatusCode;

/**
 * @author xiaojixiang
 * @version v1.0
 * @description 失败错误消息
 * @date 18-7-6
 */
public class FailedResponseData extends ResponseData {

    public FailedResponseData(String failedMessage) {
        super(OptStatusCode.OPT_ERROR, failedMessage);
    }

    public FailedResponseData() {
        super(OptStatusCode.OPT_ERROR, "failed");
    }
}
