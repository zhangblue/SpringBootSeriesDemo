package com.zhangblue.administrator.validator.model;

import com.zhangblue.administrator.validator.validator.PhoneValidator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @ClassName User
 * @Description TODO
 * @Author zhangdi
 * @Date 2019/04/04 15:24
 **/
public class User {

  @NotNull(message = "id不能为空", groups = UPDATE.class)
  @Null(message = "id必须为空", groups = SAVE.class)
  private Integer id;
  @NotBlank(message = "姓名不能为空", groups = {UPDATE.class, SAVE.class})
  private String name;
  @Min(value = 18, message = "年龄不能小于18", groups = {UPDATE.class, SAVE.class})
  private Integer age;
  private Integer create;

  @PhoneValidator(groups = {UPDATE.class, SAVE.class})
  private String phone;

  public interface SAVE {

  }

  public interface UPDATE {

  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getCreate() {
    return create;
  }

  public void setCreate(Integer create) {
    this.create = create;
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

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
}
