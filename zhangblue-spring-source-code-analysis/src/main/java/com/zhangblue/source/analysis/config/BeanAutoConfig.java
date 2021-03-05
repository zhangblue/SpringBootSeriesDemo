package com.zhangblue.source.analysis.config;

import com.zhangblue.source.analysis.beans.Bean2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangdi
 * @description: bean自动注入
 * @date 2021/3/4 下午2:51
 * @since v1.0
 **/
@Configuration
public class BeanAutoConfig {

  @Bean
  public Bean2 getBean2() {
    return new Bean2();
  }
}
