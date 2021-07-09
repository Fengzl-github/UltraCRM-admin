package com.cn.admin.api.config.cache;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 *@Author fengzhilong
 *@Date 2021/7/8 16:40
 *@Desc
 **/
@Slf4j
@EnableCaching
@Configuration
public class RedisConfig {

    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        FastJsonRedisSerializer<Object> redisSerializer = new FastJsonRedisSerializer<>(Object.class);
        // 建议使用这种方式，小范围指定白名单
        ParserConfig.getGlobalInstance().addAccept("com.cn.admin.");

        // 设置值（value）的序列化采用FastJsonRedisSerializer。
        redisTemplate.setValueSerializer(redisSerializer);
        redisTemplate.setHashValueSerializer(redisSerializer);
        // 设置键（key）的序列化采用StringRedisSerializer。
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();

        log.info(" -----------------redis配置--------------");

        return redisTemplate;
    }

    @Bean
    @ConditionalOnMissingBean(StringRedisTemplate.class)
    public StringRedisTemplate stringRedisTemplate(
            RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }


    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(){

        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();

        RedisCacheConfiguration redisCacheConfiguration = config
                .entryTtl(Duration.ofSeconds(60))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new FastJsonRedisSerializer<>(Object.class)));

        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
        cacheConfigurations.put("60s",config.entryTtl(Duration.ofSeconds(60)));
        cacheConfigurations.put("15m",config.entryTtl(Duration.ofMinutes(15)));
        cacheConfigurations.put("30m",config.entryTtl(Duration.ofMinutes(30)));
        cacheConfigurations.put("60m",config.entryTtl(Duration.ofHours(1)));
        cacheConfigurations.put("110m",config.entryTtl(Duration.ofMinutes(60)));
        cacheConfigurations.put("12h",config.entryTtl(Duration.ofHours(12)));
        cacheConfigurations.put("24h",config.entryTtl(Duration.ofDays(1)));
        cacheConfigurations.put("30Day",config.entryTtl(Duration.ofDays(30)));

        return redisCacheConfiguration;

    }

//    @Primary
    @Bean
    public RedisCacheManager ttlCacheManager(RedisConnectionFactory redisConnectionFactory) {

        log.info(" -----------------配置redis缓存管理器--------------");
        return new MyRedisCacheManager(RedisCacheWriter.lockingRedisCacheWriter(redisConnectionFactory),
                // 默认缓存配置
                this.redisCacheConfiguration());
    }


}
