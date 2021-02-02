package com.zhangblue.spring.data.elasticsearch.mapping;

import java.util.Date;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author zhangdi
 * @description: 学生表
 * @date 2021/2/2 上午10:47
 * @since 1.0
 **/
@Data
@Document(indexName = "index_student", shards = 1, replicas = 0, createIndex = true, refreshInterval = "5s")
public class Student {

  @Id
  private String id;

  @Field(name = "s_name", type = FieldType.Keyword)
  private String name;
  @Field(name = "s_age", type = FieldType.Integer)
  private int age;

  @Field(name = "m_time", type = FieldType.Date)
  private Date date;

//  @Field(name = "m_address", type = FieldType.Object)
//  private Location location;


  public Student() {
  }

  public Student(String name, int age, Date date) {
    this.name = name;
    this.age = age;
    this.date = date;
  }


}
