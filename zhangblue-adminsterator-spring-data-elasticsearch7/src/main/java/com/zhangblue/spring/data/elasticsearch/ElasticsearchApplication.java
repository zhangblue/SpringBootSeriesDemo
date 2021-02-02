package com.zhangblue.spring.data.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.config.EnableElasticsearchAuditing;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author zhangdi
 * @description: 创建表
 * @date 2021/2/2 上午10:46
 * @since 1.0
 **/
@SpringBootApplication
@EnableElasticsearchAuditing
@EnableElasticsearchRepositories
public class ElasticsearchApplication {

  public static void main(String[] args) {
    SpringApplication.run(ElasticsearchApplication.class, args);
  }


}
