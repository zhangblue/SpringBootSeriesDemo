package com.zhangblue.config;

import com.zhangblue.bean.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class MainConfigOfProfile {

  @Profile("test")
  @Bean
  public DataSource dataSourceTest() {
    DataSource dataSource = new DataSource("test");
    return dataSource;
  }

  @Profile("dev")
  @Bean
  public DataSource dataSourceDev() {
    DataSource dataSource = new DataSource("dev");
    return dataSource;
  }
}
