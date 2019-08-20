package com.zhangblue.config;

import com.zhangblue.bean.Sheep;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 *
 */
@Configuration
public class MainConfigOfAutowired {
  @Primary
  @Bean("sheep1")
  public Sheep sheep() {
    return new Sheep();
  }
}
