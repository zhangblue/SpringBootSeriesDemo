package com.zhangblue.bean;

public class DataSource {
  private String type;

  public DataSource(String type) {
    this.type = type;
  }

  public void dorun() {
    System.out.println("当前类型为" + type);
  }
}
