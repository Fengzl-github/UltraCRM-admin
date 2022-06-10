package com.cn.admin.api.config.elastic;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.cn.admin.api.config.properties.ElasticProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Fengzl
 * @Date 2022/6/2 13:50
 * @Desc
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(ElasticProperties.class)
public class ElasticSearchConfig {

    @Bean
    public ElasticsearchClient elasticsearchClient(ElasticProperties properties) {

        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(properties.getUser(), properties.getPassword()));

        log.info("-----初始化es配置-----用户:{};密码:{}", properties.getUser(), properties.getPassword());
        RestClient restClient = RestClient.builder(new HttpHost(properties.getUrl(), properties.getPort()))
                .setHttpClientConfigCallback(httpClientBuilder -> {
                    httpClientBuilder.disableAuthCaching();
                    return httpClientBuilder
                            .setDefaultCredentialsProvider(credentialsProvider);
                })
                .build();

        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        return new ElasticsearchClient(transport);





    }
}
