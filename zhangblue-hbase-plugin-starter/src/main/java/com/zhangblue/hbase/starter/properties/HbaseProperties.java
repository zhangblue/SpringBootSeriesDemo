package com.zhangblue.hbase.starter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "zhangblue.hbase")
@Data
public class HbaseProperties {
  private String namespace;
}
