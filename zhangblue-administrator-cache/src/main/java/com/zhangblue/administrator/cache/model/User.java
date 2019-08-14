package com.zhangblue.administrator.cache.model;

/**
 * @ClassName User
 * @Description TODO
 * @Author zhangdi
 * @Date 2019/04/08 10:58
 **/
public class User{

  private Integer id;
  private String name;
  private Integer age;

  public User() {
    super();
  }

  public User(Integer id, String name, Integer age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", age=" + age +
        '}';
  }
}
