package com.zhangblue.administrator.bean.order.configuration;

import com.zhangblue.administrator.bean.order.beans.Bean1;
import com.zhangblue.administrator.bean.order.beans.Bean2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author zhangdi
 * @description: bean自动装配
 * @date 2021/2/25 下午6:19
 * @since V1.0
 **/
@Configuration
public class BeansAutoConfig {


  @Order(2)
  @Bean(value = "bean2", initMethod = "init")
  public Bean2 getBean2() {
    return new Bean2();
  }

  @Order(1)
  @Bean("bean1")
  public Bean1 getBean1() {
    return new Bean1();
  }
}
