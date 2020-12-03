package com.zhangblue.administrator.cache.configuration;

import com.zhangblue.administrator.cache.model.Commodity;
import com.zhangblue.administrator.cache.model.User;
import java.lang.reflect.Method;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.Arrays;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

/**
 * @ClassName MyRedisConfig
 * @Description TODO
 * @Author zhangdi
 * @Date 2019/04/16 16:08
 **/
@Configuration
public class RedisCacheConfig {

  //  @Bean(value = "userRedisTemplate")
  public RedisTemplate<Object, User> userRedisTemplate(
      RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
    RedisTemplate<Object, User> template = new RedisTemplate<>();
    template.setConnectionFactory(redisConnectionFactory);
    Jackson2JsonRedisSerializer<User> userJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<User>(
        User.class);
    template.setDefaultSerializer(userJackson2JsonRedisSerializer);
    return template;
  }

  //  @Bean(name = "userRedisCacheManager")
  public RedisCacheManager userCacheManager(RedisConnectionFactory redisConnectionFactory) {
    RedisCacheWriter redisCacheWriter = RedisCacheWriter
        .nonLockingRedisCacheWriter(redisConnectionFactory);
    Jackson2JsonRedisSerializer<User> userJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
        User.class);
    RedisSerializationContext.SerializationPair<User> pair = RedisSerializationContext.SerializationPair
        .fromSerializer(userJackson2JsonRedisSerializer);
    RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
        .serializeValuesWith(pair);
    RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
    return cacheManager;
  }

  @Bean(name = "commodityRedisCacheManager")
  public RedisCacheManager commodityCacheManager(RedisConnectionFactory redisConnectionFactory) {

    RedisCacheWriter redisCacheWriter = RedisCacheWriter
        .nonLockingRedisCacheWriter(redisConnectionFactory);
    Jackson2JsonRedisSerializer<Commodity> userJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
        Commodity.class);
    RedisSerializationContext.SerializationPair<Commodity> pair = RedisSerializationContext.SerializationPair
        .fromSerializer(userJackson2JsonRedisSerializer);
    RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
        .entryTtl(
            Duration.ofHours(1)).serializeValuesWith(pair);
    RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
    return cacheManager;
  }

  @Bean(name = "myKeyGenerator")
  public KeyGenerator keyGenerator() {
    return new KeyGenerator() {
      @Override
      public Object generate(Object target, Method method, Object... params) {
        return method.getName() + "[" + Arrays.asList(params).toString() + "]";
      }
    };
  }
}

