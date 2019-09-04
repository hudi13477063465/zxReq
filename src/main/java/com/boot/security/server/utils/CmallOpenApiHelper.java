package com.boot.security.server.utils;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.cloudapi.sdk.client.ApacheHttpClient;
import com.alibaba.cloudapi.sdk.constant.SdkConstant;
import com.alibaba.cloudapi.sdk.enums.HttpMethod;
import com.alibaba.cloudapi.sdk.enums.ParamPosition;
import com.alibaba.cloudapi.sdk.enums.Scheme;
import com.alibaba.cloudapi.sdk.model.ApiRequest;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.alibaba.cloudapi.sdk.model.HttpClientBuilderParams;
import com.boot.security.server.constants.CmallConstants;

public class CmallOpenApiHelper extends ApacheHttpClient {

    public final static String HOST     = "cmallopenapi.citic-mall.com";

    static CmallOpenApiHelper  instance = new CmallOpenApiHelper();


    public static CmallOpenApiHelper getInstance() {
        return instance;
    }

    public void init() {
        HttpClientBuilderParams httpClientBuilderParams = new HttpClientBuilderParams();
        httpClientBuilderParams.setScheme(Scheme.HTTPS);
        httpClientBuilderParams.setHost(HOST);
        httpClientBuilderParams.setAppKey(CmallConstants.APPKEY);
        httpClientBuilderParams.setAppSecret(CmallConstants.APPSECRET);
        super.init(httpClientBuilderParams);
    }

    /**
     * subUrl 路径 v 版本号 appId
     */
    public ApiResponse sendCmallOpenApisyncMode(String subUrl, String v, String appId,String rev) {
        //String path = "/openapi/getcate";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM, subUrl);
        request.addParam("appId", appId, ParamPosition.HEAD, true);
        request.addParam("v", v, ParamPosition.HEAD, true);
        if(StringUtils.isBlank(rev)){
        	rev = "TEST";
        }
        request.addParam("X-Ca-Stage", rev, ParamPosition.HEAD, true);
        return sendSyncRequest(request);
    }
    
    /**
     * subUrl 路径 v 版本号 appId
     */
    public ApiResponse sendCmallOpenApisyncMode(String subUrl, String v, String appId,String rev,Map<String,String> params) {
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM, subUrl);
        request.addParam("appId", appId, ParamPosition.HEAD, true);
        request.addParam("v", v, ParamPosition.HEAD, true);
        if(StringUtils.isBlank(rev)){
            rev = "TEST";
        }
        request.addParam("X-Ca-Stage", rev, ParamPosition.HEAD, true);
        for(Entry<String,String> entry:params.entrySet()){
            request.addParam(entry.getKey(), entry.getValue(), ParamPosition.BODY, true);
        }
        return sendSyncRequest(request);
    }
    
    public  String getResultString(ApiResponse response) throws IOException {
        StringBuilder result = new StringBuilder();
        result.append("Response from backend server").append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
        result.append("ResultCode:").append(SdkConstant.CLOUDAPI_LF).append(response.getCode()).append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
        if(response.getCode() != 200){
            result.append("Error description:").append(response.getHeaders().get("X-Ca-Error-Message")).append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
        }

        result.append("ResultBody:").append(SdkConstant.CLOUDAPI_LF).append(new String(response.getBody() , SdkConstant.CLOUDAPI_ENCODING));

        return result.toString();
    }
}
