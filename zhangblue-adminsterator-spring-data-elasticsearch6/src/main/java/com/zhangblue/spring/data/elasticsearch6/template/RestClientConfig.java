package com.zhangblue.spring.data.elasticsearch6.template;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * @author zhangdi
 * @description: 模版定义
 * @date 2021/2/2 上午11:11
 * @since
 **/
@Configuration
@Slf4j
public class RestClientConfig extends AbstractElasticsearchConfiguration {

  @Bean
  @Override
  public RestHighLevelClient elasticsearchClient() {
    final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
        .connectedTo("172.16.36.134:9200") //设置集群地址
        .withConnectTimeout(Duration.ofSeconds(5)) //设置链接超时时间
        .withSocketTimeout(Duration.ofSeconds(3)) //设置请求超时时间
        .withBasicAuth("username", "password") //设置认证身份
        .build();
    return RestClients.create(clientConfiguration).rest();
  }
}
