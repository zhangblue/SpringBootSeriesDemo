package com.zhangblue.config;

import com.zhangblue.bean.Sheep;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfigOfLifeCycle4 {

  @Bean
  public Sheep sheep() {
    return new Sheep();
  }
}
