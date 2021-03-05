package com.zhangblue.administrator.bean.order.beans;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangdi
 * @description: bean2
 * @date 2021/2/25 下午6:18
 * @since v1.0
 **/
public class Bean2 {

  @Autowired
  private Bean1 bean1;

  public Bean2() {
    System.out.println("初始化bean2");
  }


  public void init() {
    bean1.sout();
  }
}
