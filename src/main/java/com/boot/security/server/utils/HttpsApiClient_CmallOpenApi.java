//
//  Created by  fred on 2017/1/12.
//  Copyright © 2016年 Alibaba. All rights reserved.
//

package com.boot.security.server.utils;

import com.alibaba.cloudapi.sdk.client.ApacheHttpClient;
import com.alibaba.cloudapi.sdk.enums.HttpMethod;
import com.alibaba.cloudapi.sdk.enums.ParamPosition;
import com.alibaba.cloudapi.sdk.enums.Scheme;
import com.alibaba.cloudapi.sdk.model.ApiCallback;
import com.alibaba.cloudapi.sdk.model.ApiRequest;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.alibaba.cloudapi.sdk.model.HttpClientBuilderParams;
import com.alibaba.cloudapi.sdk.enums.WebSocketApiType;
import com.alibaba.fastjson.JSONObject;

public class HttpsApiClient_CmallOpenApi extends ApacheHttpClient{
    public final static String HOST = "cmallopenapi.citic-mall.com";
    static HttpsApiClient_CmallOpenApi instance = new HttpsApiClient_CmallOpenApi();
    public static HttpsApiClient_CmallOpenApi getInstance(){return instance;}

    public void init(HttpClientBuilderParams httpClientBuilderParams){
        httpClientBuilderParams.setScheme(Scheme.HTTPS);
        httpClientBuilderParams.setHost(HOST);
        super.init(httpClientBuilderParams);
    }



    public void 获取订单(String v , String appId , String orderSn , ApiCallback callback) {
        String path = "/openapi/getorderbysn";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("v" , v , ParamPosition.HEAD , true);
        request.addParam("appId" , appId , ParamPosition.HEAD , true);
        request.addParam("orderSn" , orderSn , ParamPosition.QUERY , true);



        sendAsyncRequest(request , callback);
    }

    public ApiResponse 获取订单_syncMode(String v , String appId , String orderSn) {
        String path = "/openapi/getorderbysn";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("v" , v , ParamPosition.HEAD , true);
        request.addParam("appId" , appId , ParamPosition.HEAD , true);
        request.addParam("orderSn" , orderSn , ParamPosition.QUERY , true);



        return sendSyncRequest(request);
    }
    public void 规格信息查询(String appId , String v , ApiCallback callback) {
        String path = "/openapi/getnorm";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("appId" , appId , ParamPosition.HEAD , false);
        request.addParam("v" , v , ParamPosition.HEAD , false);



        sendAsyncRequest(request , callback);
    }

    public ApiResponse 规格信息查询_syncMode(String appId , String v) {
        String path = "/openapi/getnorm";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("appId" , appId , ParamPosition.HEAD , false);
        request.addParam("v" , v , ParamPosition.HEAD , false);



        return sendSyncRequest(request);
    }
    public void 获取订单_批量(String v , String appId , String startTime , String endTime , Integer pageIndex , Integer pageCount , ApiCallback callback) {
        String path = "/openapi/getorders";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("v" , v , ParamPosition.HEAD , true);
        request.addParam("appId" , appId , ParamPosition.HEAD , true);
        request.addParam("startTime" , startTime , ParamPosition.QUERY , true);
        request.addParam("endTime" , endTime , ParamPosition.QUERY , true);
        request.addParam("pageIndex" , String.valueOf(pageIndex) , ParamPosition.QUERY , true);
        request.addParam("pageCount" , String.valueOf(pageCount) , ParamPosition.QUERY , true);



        sendAsyncRequest(request , callback);
    }

    public ApiResponse 获取订单_批量_syncMode(String v , String appId , String startTime , String endTime , Integer pageIndex , Integer pageCount) {
        String path = "/openapi/getorders";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("v" , v , ParamPosition.HEAD , true);
        request.addParam("appId" , appId , ParamPosition.HEAD , true);
        request.addParam("startTime" , startTime , ParamPosition.QUERY , true);
        request.addParam("endTime" , endTime , ParamPosition.QUERY , true);
        request.addParam("pageIndex" , String.valueOf(pageIndex) , ParamPosition.QUERY , true);
        request.addParam("pageCount" , String.valueOf(pageCount) , ParamPosition.QUERY , true);



        return sendSyncRequest(request);
    }
    public void 店铺分类查询(String v , String appId , ApiCallback callback) {
        String path = "/openapi/getsellercate";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("v" , v , ParamPosition.HEAD , true);
        request.addParam("appId" , appId , ParamPosition.HEAD , true);



        sendAsyncRequest(request , callback);
    }

    public ApiResponse 店铺分类查询_syncMode(String v , String appId) {
        String path = "/openapi/getsellercate";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("v" , v , ParamPosition.HEAD , true);
        request.addParam("appId" , appId , ParamPosition.HEAD , true);



        return sendSyncRequest(request);
    }
    public void 商品新增(String v , String appId , String productData , ApiCallback callback) {
        String path = "/openapi/createprd";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("v" , v , ParamPosition.HEAD , true);
        request.addParam("appId" , appId , ParamPosition.HEAD , true);
        request.addParam("productData" , productData , ParamPosition.BODY , true);



        sendAsyncRequest(request , callback);
    }

    public ApiResponse 商品新增_syncMode(String v , String appId , String productData) {
        String path = "/openapi/createprd";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("v" , v , ParamPosition.HEAD , true);
        request.addParam("appId" , appId , ParamPosition.HEAD , true);
        request.addParam("productData" , productData , ParamPosition.BODY , true);



        return sendSyncRequest(request);
    }
    public void 商品修改(String v , String appId , String productData , ApiCallback callback) {
        String path = "/openapi/updateprd";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("v" , v , ParamPosition.HEAD , true);
        request.addParam("appId" , appId , ParamPosition.HEAD , true);
        request.addParam("productData" , productData , ParamPosition.BODY , true);



        sendAsyncRequest(request , callback);
    }

    public ApiResponse 商品修改_syncMode(String v , String appId , String productData) {
        String path = "/openapi/updateprd";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("v" , v , ParamPosition.HEAD , true);
        request.addParam("appId" , appId , ParamPosition.HEAD , true);
        request.addParam("productData" , productData , ParamPosition.BODY , true);



        return sendSyncRequest(request);
    }
    public void 商品属性查询(String v , String appId , ApiCallback callback) {
        String path = "/openapi/getprdattr";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("v" , v , ParamPosition.HEAD , true);
        request.addParam("appId" , appId , ParamPosition.HEAD , true);



        sendAsyncRequest(request , callback);
    }

    public ApiResponse 商品属性查询_syncMode(String v , String appId) {
        String path = "/openapi/getprdattr";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("v" , v , ParamPosition.HEAD , true);
        request.addParam("appId" , appId , ParamPosition.HEAD , true);



        return sendSyncRequest(request);
    }
    public void 商品增加SKU(String v , String appId , String productData , ApiCallback callback) {
        String path = "/openapi/createprdsku";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("v" , v , ParamPosition.HEAD , true);
        request.addParam("appId" , appId , ParamPosition.HEAD , true);
        request.addParam("productData" , productData , ParamPosition.BODY , true);



        sendAsyncRequest(request , callback);
    }

    public ApiResponse 商品增加SKU_syncMode(String v , String appId , String productData) {
        String path = "/openapi/createprdsku";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("v" , v , ParamPosition.HEAD , true);
        request.addParam("appId" , appId , ParamPosition.HEAD , true);
        request.addParam("productData" , productData , ParamPosition.BODY , true);



        return sendSyncRequest(request);
    }
    public void 商品库存更新(String v , String appId , String productData , ApiCallback callback) {
        String path = "/openapi/updateprdstock";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("v" , v , ParamPosition.HEAD , true);
        request.addParam("appId" , appId , ParamPosition.HEAD , true);
        request.addParam("productData" , productData , ParamPosition.BODY , true);



        sendAsyncRequest(request , callback);
    }

    public ApiResponse 商品库存更新_syncMode(String v , String appId , String productData) {
        String path = "/openapi/updateprdstock";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("v" , v , ParamPosition.HEAD , true);
        request.addParam("appId" , appId , ParamPosition.HEAD , true);
        request.addParam("productData" , productData , ParamPosition.BODY , true);



        return sendSyncRequest(request);
    }
    public void 店铺运费模板查询(String v , String appId , ApiCallback callback) {
        String path = "/openapi/gettransfee";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("v" , v , ParamPosition.HEAD , true);
        request.addParam("appId" , appId , ParamPosition.HEAD , true);



        sendAsyncRequest(request , callback);
    }

    public ApiResponse 店铺运费模板查询_syncMode(String v , String appId) {
        String path = "/openapi/gettransfee";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("v" , v , ParamPosition.HEAD , true);
        request.addParam("appId" , appId , ParamPosition.HEAD , true);



        return sendSyncRequest(request);
    }
    public void 品牌查询接口(String v , String appId , ApiCallback callback) {
        String path = "/openapi/getbrand";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("v" , v , ParamPosition.HEAD , true);
        request.addParam("appId" , appId , ParamPosition.HEAD , true);



        sendAsyncRequest(request , callback);
    }

    public ApiResponse 品牌查询接口_syncMode(String v , String appId) {
        String path = "/openapi/getbrand";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("v" , v , ParamPosition.HEAD , true);
        request.addParam("appId" , appId , ParamPosition.HEAD , true);



        return sendSyncRequest(request);
    }
    public void 商品上下架(String v , String appId , String productData , ApiCallback callback) {
        String path = "/openapi/upordownprd";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("v" , v , ParamPosition.HEAD , true);
        request.addParam("appId" , appId , ParamPosition.HEAD , true);
        request.addParam("productData" , productData , ParamPosition.BODY , true);



        sendAsyncRequest(request , callback);
    }

    public ApiResponse 商品上下架_syncMode(String v , String appId , String productData) {
        String path = "/openapi/upordownprd";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("v" , v , ParamPosition.HEAD , true);
        request.addParam("appId" , appId , ParamPosition.HEAD , true);
        request.addParam("productData" , productData , ParamPosition.BODY , true);



        return sendSyncRequest(request);
    }

}