package com.boot.security.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.boot.security.server.utils.HttpUtils;

@RestController
@RequestMapping("api/zx")
public class ZhongXinReqController {


	@GetMapping("/demo")
	@ResponseBody
	public Map<String,Object> request(String url) {

        Map<String, Object> m = new HashMap<String, Object>();
        // 请求参数
        m.put("v", "1.0");
        m.put("appId", "110570864");
        System.out.println(m);
        String result = null;
        try {
            //String url = "http://cmallopenapi.citic-mall.com/openapi/getbrand";
            result = HttpUtils.URLPost(url, m);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(result);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("result", result);
        return map;
	}


}
