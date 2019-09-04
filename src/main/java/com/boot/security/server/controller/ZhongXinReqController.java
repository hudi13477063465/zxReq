package com.boot.security.server.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.HttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.boot.security.server.constants.CmallConstants;
import com.boot.security.server.utils.CmallOpenApiHelper;
import com.boot.security.server.utils.RequestUtil;

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
    
    /**
     * 适用于商品新增 修改 ，商品sku新增，上下架
     */
    @PostMapping("/handle")
    @ResponseBody
    public String request(HttpServletRequest request) throws IOException {
        Map<String, String> map =RequestUtil.getParameterMap(request);
        for(Entry<String, String> entry:map.entrySet()){
            System.out.println(entry.getKey()+"========"+entry.getValue());
        }
        CmallOpenApiHelper helper = CmallOpenApiHelper.getInstance();
        helper.init();
        ApiResponse response = helper.sendCmallOpenApisyncMode(map.get("url"), CmallConstants.V,map.get("appId"),map.get("rev"),map);
        return helper.getResultString(response);
    }
}
