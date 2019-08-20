package com.zhangblue.bean;

import org.springframework.beans.factory.annotation.Value;

public class Sheep {
  @Value("zhangsan")
  private String name;
  @Value("#{20-2}")
  private int age;
  @Value("${sheep.color}")
  private String color;
}
