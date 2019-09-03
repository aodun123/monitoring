package com.cn.uvfortune.infrastructure.transport.response.impl;


import com.cn.uvfortune.infrastructure.transport.response.OptStatusCode;

/**
 * @author xiaojixiang
 * @version v1.0
 * @description 成功消息
 * @date 18-7-6
 */
public class SuccessResponseData extends ResponseData {

    public SuccessResponseData() {
        super(OptStatusCode.OPT_SUCCESS, "successful");
    }
}
