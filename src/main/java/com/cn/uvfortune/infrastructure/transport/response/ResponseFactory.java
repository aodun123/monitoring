package com.cn.uvfortune.infrastructure.transport.response;


import com.cn.uvfortune.infrastructure.transport.response.impl.FailedResponseData;
import com.cn.uvfortune.infrastructure.transport.response.impl.ResponseData;
import com.cn.uvfortune.infrastructure.transport.response.impl.SuccessResponseData;
import com.cn.uvfortune.infrastructure.transport.response.impl.VerifyErrorResponseData;

/**
 * @author xiaojixiang
 * @version v1.0
 * @description Response工厂
 * @date 18-7-19
 */
public final class ResponseFactory {

    public static ResponseData createCustomResponse(OptStatusCode optStatusCode, String message) {
        return new ResponseData(optStatusCode, message);
    }

    public static SuccessResponseData createSuccessResponse() {
        return new SuccessResponseData();
    }

    public static FailedResponseData createFaildResponse() {
        return new FailedResponseData();
    }

    public static VerifyErrorResponseData createVerifyErrorResponse(String verifyErrorMessage) {
        return new VerifyErrorResponseData(verifyErrorMessage);
    }
}
