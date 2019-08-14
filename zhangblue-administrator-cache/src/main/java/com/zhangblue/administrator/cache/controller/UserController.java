package com.zhangblue.administrator.cache.controller;

import com.zhangblue.administrator.cache.model.User;
import com.zhangblue.administrator.cache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author zhangdi
 * @Date 2019/04/08 10:58
 **/
@RestController
@RequestMapping("/controller/user")
public class UserController {

  @Autowired
  private UserService userService;


  @GetMapping(value = "/getone")
  public User getUserById(final int id) {
    return userService.getUserById(id);
  }


  @GetMapping(value = "/update")
  public User updateUser(User user) {
    User user1 = userService.updateUser(user);
    return user1;
  }

  @GetMapping(value = "/delete")
  public void deleteUser(int id) {
    userService.deleteUser(id);
  }

  @GetMapping(value = "/getByName")
  public User getUserByName(String name){
    User userByName = userService.getUserByName(name);
    return userByName;
  }

}
