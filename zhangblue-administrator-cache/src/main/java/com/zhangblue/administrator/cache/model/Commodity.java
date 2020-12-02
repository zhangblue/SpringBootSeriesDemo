package com.zhangblue.administrator.cache.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhangdi
 */
@Data
@Accessors(chain = true)
public class Commodity {

  private Integer id;
  private String name;
  private Integer price;


  public Commodity() {
  }

  public Commodity(Integer id, String name, Integer price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }

}