package com.cn.uvfortune.infrastructure.transport.response.impl;


import com.cn.uvfortune.infrastructure.transport.response.OptStatusCode;

/**
 * @author xiaojixiang
 * @version v1.0
 * @description 验证错误消息
 * @date 18-7-6
 */
public class VerifyErrorResponseData extends ResponseData {

    public VerifyErrorResponseData(String verifyErrorMessage) {
        super(OptStatusCode.OPT_VERIFY_ERROR, verifyErrorMessage);
    }
}
