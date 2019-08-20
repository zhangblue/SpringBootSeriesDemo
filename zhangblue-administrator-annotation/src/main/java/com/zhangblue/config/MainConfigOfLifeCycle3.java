package com.zhangblue.config;

import com.zhangblue.bean.Birds;
import com.zhangblue.bean.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfigOfLifeCycle3 {

  @Bean
  public Dog dog() {
    return new Dog();
  }

  @Bean
  public Birds birds() {
    return new Birds();
  }
}
