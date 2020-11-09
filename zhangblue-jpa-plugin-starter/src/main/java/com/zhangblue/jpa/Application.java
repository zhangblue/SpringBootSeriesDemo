package com.zhangblue.jpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author di.zhang
 * @date 2020/5/14
 * @time 13:55
 **/
@Slf4j
@EnableJpaAuditing
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication springApplication = new SpringApplication(Application.class);
    springApplication.setBannerMode(Mode.OFF);
    springApplication.run(args);
  }
}
