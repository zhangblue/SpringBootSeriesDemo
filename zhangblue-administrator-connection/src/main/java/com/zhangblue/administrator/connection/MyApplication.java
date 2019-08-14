package com.zhangblue.administrator.connection;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MyApplication {
  /**
   * this is the only main, start project.
   */
  public static void main(String[] args) {
    SpringApplication springApplication = new SpringApplication(MyApplication.class);
    springApplication.run(args);
  }
}
