package com.zhangblue.spring.data.elasticsearch6.mapping;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author zhangdi
 * @description: 地理位置
 * @date 2021/2/2 上午11:05
 * @since v1.0
 **/
public class Location {

  @Field(name = "city", type = FieldType.Keyword)
  private String city;//市
  @Field(name = "province", type = FieldType.Keyword)
  private String province; //省
  @Field(name = "county", type = FieldType.Keyword)
  private String county;//区

  public Location(String city, String province, String county) {
    this.city = city;
    this.province = province;
    this.county = county;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCounty() {
    return county;
  }

  public void setCounty(String county) {
    this.county = county;
  }
}
