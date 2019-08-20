package com.zhangblue.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Dog {

  public Dog() {
    System.out.println("Dog constructor.....");
  }

  //对象创建并赋值之后调用
  @PostConstruct
  public void init() {
    System.out.println("Dog ..... @PostConstruct...");
  }

  //在容器移除对象之前，调用此方法
  @PreDestroy
  public void destory() {
    System.out.println("Dog .... @PreDestroy....");
  }
}
