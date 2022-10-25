package com.cn.admin.api.config.prometheus;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;

/**
 * @Author Fengzl
 * @Date 2022/6/5 18:42
 * @Desc
 **/
@RestController
@RequestMapping("/prometheus")
public class PrometheusController {

    @Value("${server.port}")
    private String serverPort;


    /**
     * 因整体项目配置了fastjson序列化参数,导致/actuator/prometheus返回格式改变从而Prometheus识别不到
     * 因此对/actuator/prometheus进行路径转换
     * @param response
     */
    @GetMapping(path = "/metrics", produces = MediaType.TEXT_PLAIN_VALUE)
    public void healthz(HttpServletResponse response) {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder prometheusUrl = new StringBuilder("http://127.0.0.1:");
        prometheusUrl.append(serverPort);
        prometheusUrl.append("/actuator/prometheus");

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(prometheusUrl.toString(), String.class);
        String body = responseEntity.getBody();
        String s = JSON.parseObject(body, String.class);

        try (BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream())) {
            bos.write(s.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
