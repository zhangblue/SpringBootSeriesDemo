package com.zhangblue.administrator.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @ClassName CacheApplication
 * @Description TODO
 * @Author zhangdi
 * @Date 2019/04/08 10:55
 **/
@SpringBootApplication
@EnableCaching
public class CacheApplication {
  public static void main(String[] args) {
    SpringApplication.run(CacheApplication.class, args);
  }
}
