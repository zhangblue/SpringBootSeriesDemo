package com.zhangblue.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Birds {

  public Birds() {
    System.out.println("Birds constructor.....");
  }

  //对象创建并赋值之后调用
  @PostConstruct
  public void init() {
    System.out.println("Birds ..... @PostConstruct...");
  }

  //在容器移除对象之前，调用此方法
  @PreDestroy
  public void destory() {
    System.out.println("Birds .... @PreDestroy....");
  }
}
