package com.cn.admin.api.config.prometheus;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @Author Fengzl
 * @Date 2022/6/5 18:42
 * @Desc
 **/
@Slf4j
@RestController
@RequestMapping("/prometheus")
public class PrometheusController {

    @GetMapping(path = "/metrics", produces = MediaType.TEXT_PLAIN_VALUE)
    public void healthz(HttpServletResponse response) throws URISyntaxException {
        log.info("Prometheus - 返回数据格式转换");
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder prometheusUrl = new StringBuilder("http://192.168.0.114:9060");
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
