package com.zhangblue.spring.data.elasticsearch6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author zhangdi
 * @description: 创建表
 * @date 2021/2/2 上午10:46
 * @since 1.0
 **/
@SpringBootApplication
@EnableElasticsearchRepositories
public class Elasticsearch6Application {

  public static void main(String[] args) {
    SpringApplication.run(Elasticsearch6Application.class, args);
  }


}
