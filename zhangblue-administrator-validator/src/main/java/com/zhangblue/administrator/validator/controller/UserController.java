package com.zhangblue.administrator.validator.controller;

import com.zhangblue.administrator.validator.model.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author zhangdi
 * @Date 2019/04/04 11:18
 **/
@RestController
@RequestMapping("/validator/")
public class UserController {

  @GetMapping(value = "/user/save")
  public String saveUser(@Validated(value = User.SAVE.class) User user, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "验证失败：" + bindingResult.getFieldError().getDefaultMessage();
    }
    return "保存成功";
  }

  @GetMapping(value = "/user/update")
  public String updateUser(@Validated(value = User.UPDATE.class) User user, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "验证失败：" + bindingResult.getFieldError().getDefaultMessage();
    }
    return "保存成功";
  }
}
