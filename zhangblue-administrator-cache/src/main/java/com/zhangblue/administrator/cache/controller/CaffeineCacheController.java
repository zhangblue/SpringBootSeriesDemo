package com.zhangblue.administrator.cache.controller;

import com.zhangblue.administrator.cache.service.CaffeineCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangdi
 * @description: CaffeineCache controller
 * @date 2021/2/23 上午10:51
 * @since
 **/
@RestController
@RequestMapping("/controller/caffeine")
public class CaffeineCacheController {

  @Autowired
  private CaffeineCacheService caffeineCacheService;

  @GetMapping(value = "/get1")
  public int getCaffeineCache1(String page) {
    int id = caffeineCacheService.getId1(page);
    return id;
  }

  @GetMapping(value = "/get2")
  public int getCaffeineCache2(String page) {
    int id = caffeineCacheService.getId2(page);
    return id;
  }
}
