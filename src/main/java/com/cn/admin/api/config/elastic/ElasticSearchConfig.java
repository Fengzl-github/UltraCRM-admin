package com.cn.admin.api.config.elastic;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.cn.admin.api.config.properties.ElasticProperties;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Fengzl
 * @Date 2022/6/2 13:50
 * @Desc
 */
@Configuration
@EnableConfigurationProperties(ElasticProperties.class)
public class ElasticSearchConfig {

    @Bean
    public ElasticsearchClient elasticsearchClient(ElasticProperties properties) {

        RestClient restClient = RestClient.builder(new HttpHost(properties.getUrl(), properties.getPort())).build();

        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        return new ElasticsearchClient(transport);

    }
}
