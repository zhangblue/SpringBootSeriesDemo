package com.zhangblue.source.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author zhangdi
 * @description: 测试ApplicationContextInitializer
 * @date 2021/3/4 下午3:20
 * @since v1.0
 **/
public class TestApplicationContextInitializer implements ApplicationContextInitializer {

  @Override
  public void initialize(ConfigurableApplicationContext applicationContext) {
    System.out.println("执行了 TestApplicationContextInitializer!!!");
  }
}
