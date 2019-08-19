package com.boot.security.server.controller;

import java.io.IOException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.boot.security.server.constants.CmallConstants;
import com.boot.security.server.utils.CmallOpenApiHelper_CmallOpenApi;

@RestController
@RequestMapping("api/zx")
public class ZhongXinReqController {

    @GetMapping("/call")
    @ResponseBody
    public String request(String url,String appId) throws IOException {
        
        url = "/"+url;
        System.out.println("路径========"+url);
        System.out.println("appid========"+appId);
        CmallOpenApiHelper_CmallOpenApi helper = CmallOpenApiHelper_CmallOpenApi.getInstance();
        helper.init();
        ApiResponse response = helper.sendCmallOpenApisyncMode(url, CmallConstants.V,appId);
        return helper.getResultString(response);
    }

}
