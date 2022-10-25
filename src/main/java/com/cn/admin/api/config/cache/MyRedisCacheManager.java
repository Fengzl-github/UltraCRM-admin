package com.cn.admin.api.config.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.util.StringUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.time.Duration;

/**
 *@Author fengzhilong
 *@Date 2021/7/9 11:50
 *@Desc 自定义redis缓存管理器
 **/
@Slf4j
public class MyRedisCacheManager extends RedisCacheManager {
    public MyRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }


    /**
     * @Author fengzhilong
     * @Desc 设置redis缓存失效时间
     *       注：@Cacheable(chacheNames="menu#60*60*24") '#'后为失效时长(秒)
     * @Date 2021/7/9 13:51
     * @param name
	 * @param cacheConfig
     * @return org.springframework.data.redis.cache.RedisCache
     **/
    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
        String[] cells = StringUtils.delimitedListToStringArray(name, "#");
        name = cells[0];
        long ttl = 60;
        if (cells.length > 1){
            String strTtl = cells[1];
            ScriptEngineManager engineManager = new ScriptEngineManager();
            ScriptEngine scriptEngine = engineManager.getEngineByName("js");
            try {
                Object result = scriptEngine.eval(strTtl);
                ttl = Long.parseLong(String.valueOf(result));
            } catch (ScriptException e) {
                e.printStackTrace();
            }
            cacheConfig = cacheConfig.entryTtl(Duration.ofSeconds(ttl));
        }
        log.info("[redis缓存] ## name -> {}, ttl -> {}(s)", name, ttl);
        return super.createRedisCache(name, cacheConfig);
    }
}
