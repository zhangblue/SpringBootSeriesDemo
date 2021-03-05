package com.zhangblue.admin.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author zhangdi
 * @description: 测试ApplicationListener
 * @date 2021/3/4 下午3:21
 * @since 1.0
 **/
public class TestApplicationListener implements ApplicationListener {

  @Override
  public void onApplicationEvent(ApplicationEvent event) {
    System.out.println("执行了 TestApplicationListener ");
  }
}
