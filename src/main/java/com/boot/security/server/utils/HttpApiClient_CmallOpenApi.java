//
//  Created by  fred on 2017/1/12.
//  Copyright © 2016年 Alibaba. All rights reserved.
//

package com.boot.security.server.utils;
import com.alibaba.cloudapi.sdk.client.ApacheHttpClient;
import com.alibaba.cloudapi.sdk.enums.Scheme;
import com.alibaba.cloudapi.sdk.enums.HttpMethod;
import com.alibaba.cloudapi.sdk.model.ApiRequest;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.alibaba.cloudapi.sdk.model.ApiCallback;
import com.alibaba.cloudapi.sdk.model.HttpClientBuilderParams;
import com.alibaba.cloudapi.sdk.enums.ParamPosition;
import com.alibaba.cloudapi.sdk.enums.WebSocketApiType;
import com.alibaba.fastjson.JSONObject;


public class HttpApiClient_CmallOpenApi extends ApacheHttpClient{
    public final static String HOST = "cmallopenapi.citic-mall.com";
    static HttpApiClient_CmallOpenApi instance = new HttpApiClient_CmallOpenApi();
    public static HttpApiClient_CmallOpenApi getInstance(){return instance;}

    public void init(HttpClientBuilderParams httpClientBuilderParams){
        httpClientBuilderParams.setScheme(Scheme.HTTP);
        httpClientBuilderParams.setHost(HOST);
        super.init(httpClientBuilderParams);
    }




    public void 订单发货状态同步(String v , String appId , String deliverInfo , ApiCallback callback) {
        String path = "/openapi/orderdeliver";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("v" , v , ParamPosition.HEAD , true);
        request.addParam("appId" , appId , ParamPosition.HEAD , true);
        request.addParam("deliverInfo" , deliverInfo , ParamPosition.BODY , true);



        sendAsyncRequest(request , callback);
    }

    public ApiResponse 订单发货状态同步_syncMode(String v , String appId , String deliverInfo) {
        String path = "/openapi/orderdeliver";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("v" , v , ParamPosition.HEAD , true);
        request.addParam("appId" , appId , ParamPosition.HEAD , true);
        request.addParam("deliverInfo" , deliverInfo , ParamPosition.BODY , true);



        return sendSyncRequest(request);
    }
    public void 后端类目查询(String appId , String v , ApiCallback callback) {
        String path = "/openapi/getcate";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("appId" , appId , ParamPosition.HEAD , true);
        request.addParam("v" , v , ParamPosition.HEAD , true);



        sendAsyncRequest(request , callback);
    }

    public ApiResponse 后端类目查询_syncMode(String appId , String v) {
        String path = "/openapi/getcate";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("appId" , appId , ParamPosition.HEAD , true);
        request.addParam("v" , v , ParamPosition.HEAD , true);



        return sendSyncRequest(request);
    }

}