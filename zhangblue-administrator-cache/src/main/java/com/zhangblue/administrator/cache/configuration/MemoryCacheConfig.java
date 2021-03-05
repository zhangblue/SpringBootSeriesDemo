package com.zhangblue.administrator.cache.configuration;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.time.Duration;
import java.util.Arrays;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangdi
 * @description: 内存cache
 * @date 2021/2/23 上午10:20
 * @since v1.0
 **/
@Configuration
public class MemoryCacheConfig {

  @Bean(name = "caffeineCacheManager")
  public CacheManager caffeineCacheManager() {
    SimpleCacheManager cacheManager = new SimpleCacheManager();

    Cache<Object, Object> build = Caffeine.newBuilder().initialCapacity(1)
        .maximumSize(2)
        .removalListener(new MyRemovalListener())
        .expireAfterAccess(
            Duration.ofSeconds(10)).build();
    cacheManager.setCaches(
        Arrays.asList(new CaffeineCache("caff1", build), new CaffeineCache("caff2", build)));
    return cacheManager;
  }

}
