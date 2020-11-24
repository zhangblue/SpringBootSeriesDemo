package com.zhangblue.administrator.cache.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Configuration;

/**
 * CaffeineCache
 *
 * @author zhangd
 */
@Configuration
@EnableCaching
public class CaffeineCacheConfig {

  public static final int DEFAULT_MAXSIZE = 10000;
  public static final int DEFAULT_TTL = 600;

  public CacheManager caffeineCacheManager() {

    CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
    Caffeine caffeine = Caffeine.newBuilder()
        //cache的初始容量值
        .initialCapacity(100)
        //maximumSize用来控制cache的最大缓存数量，maximumSize和maximumWeight(最大权重)不可以同时使用，
        .maximumSize(1000)
        //最后一次写入或者访问后过久过期
        .expireAfterAccess(500, TimeUnit.SECONDS)
        //创建或更新之后多久刷新,需要设置cacheLoader
        .refreshAfterWrite(10, TimeUnit.SECONDS);
    caffeineCacheManager.setCaffeine(caffeine);
    caffeineCacheManager.setCacheLoader(cacheLoader);
    caffeineCacheManager.setCacheNames(names);//根据名字可以创建多个cache，但是多个cache使用相同的策略
    caffeineCacheManager.setAllowNullValues(false);//是否允许值为空
    return caffeineCacheManager;


  }
}
