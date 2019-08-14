package com.zhangblue.elasticsearch.starter.configuration;

import com.zhangblue.elasticsearch.api.ElasticSearchImplement;
import com.zhangblue.elasticsearch.api.ElasticSearchOperation;
import com.zhangblue.elasticsearch.starter.properties.ElasticSearchProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ElasticSearchProperties.class)
@Slf4j
public class ElasticSearchAutoConfiguration {

  @Bean(value = "elasticSearchImplement", initMethod = "init")
  public ElasticSearchImplement elasticSearchImplement(ElasticSearchProperties elasticSearchProperties) {
    ElasticSearchImplement elasticSearchImplement = new ElasticSearchImplement(elasticSearchProperties.getClusterName(), elasticSearchProperties.getPort(), elasticSearchProperties.getHosts());
    return elasticSearchImplement;
  }

  @Bean(value = "elasticSearchOperation")
  public ElasticSearchOperation elasticSearchOperation(ElasticSearchImplement elasticSearchImplement) {
    ElasticSearchOperation elasticSearchOperation = new ElasticSearchOperation(elasticSearchImplement);
    return elasticSearchOperation;
  }


}
