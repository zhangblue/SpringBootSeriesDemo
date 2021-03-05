package com.zhangblue.administrator.cache.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author zhangdi
 * @description: caffeine cache
 * @date 2021/2/23 上午10:48
 * @since v1.0
 **/
@Service
public class CaffeineCacheService {


  @Cacheable(cacheManager = "caffeineCacheManager", cacheNames = "caff1")
  public int getId1(String page) {
    System.out.println("id1 计算 code = " + page);
    return page.hashCode();
  }

  @Cacheable(cacheManager = "caffeineCacheManager", cacheNames = "caff2")
  public int getId2(String page) {
    System.out.println("id2 计算 code = " + page);
    return page.hashCode();
  }

}
