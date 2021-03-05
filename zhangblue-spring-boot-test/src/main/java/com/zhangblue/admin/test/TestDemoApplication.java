package com.zhangblue.admin.test;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author zhangd
 */
@SpringBootApplication
public class TestDemoApplication implements ApplicationContextAware {

  public static void main(String[] args) {
    SpringApplication.run(TestDemoApplication.class, args);
  }

  private ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }
}
