package com.boot.security.server.controller;

import java.io.IOException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.boot.security.server.constants.CmallConstants;
import com.boot.security.server.utils.CmallOpenApiHelper;

@RestController
@RequestMapping("api/zx")
public class ZhongXinReqController {

    @GetMapping("/call")
    @ResponseBody
    public String request(String url,String appId,String rev) throws IOException {
        
        url = "/"+url;
        System.out.println("路径========"+url);
        System.out.println("appId========"+appId);
        System.out.println("rev========"+rev);
        CmallOpenApiHelper helper = CmallOpenApiHelper.getInstance();
        helper.init();
        ApiResponse response = helper.sendCmallOpenApisyncMode(url, CmallConstants.V,appId,rev);
        return helper.getResultString(response);
    }

}
