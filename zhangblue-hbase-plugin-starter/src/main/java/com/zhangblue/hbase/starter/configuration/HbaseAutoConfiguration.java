package com.zhangblue.hbase.starter.configuration;

import com.zhangblue.hbase.api.HbaseImplement;
import com.zhangblue.hbase.api.HbaseOperation;
import com.zhangblue.hbase.starter.properties.HbaseProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(HbaseProperties.class)
@Slf4j
public class HbaseAutoConfiguration {

  @Bean(value = "hbaseImplement", initMethod = "init")
  public HbaseImplement hbaseImplement() {
    HbaseImplement hbaseImplement = new HbaseImplement();
    return hbaseImplement;
  }

  @Bean(value = "hbaseOperation")
  public HbaseOperation hbaseOperation(HbaseImplement hbaseImplement, HbaseProperties hbaseProperties) {
    HbaseOperation hbaseOperation = new HbaseOperation(hbaseImplement, hbaseProperties.getNamespace());
    return hbaseOperation;
  }
}
