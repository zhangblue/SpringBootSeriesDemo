package com.zhangblue.admin.test.beans;

import org.springframework.stereotype.Component;

/**
 * @author zhangdi
 * @description: bean1 测试
 * @date 2021/3/4 下午2:42
 * @since v1.0
 **/
@Component
public class Bean1 {


  public Bean1() {
    System.out.println("bean1 初始化");
  }
}
