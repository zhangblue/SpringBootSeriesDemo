package com.zhangblue.administrator.webflux.controller;

import com.zhangblue.administrator.webflux.model.User;
import com.zhangblue.administrator.webflux.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author zhangdi
 * @Date 2019/04/04 11:18
 **/
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  private final UserService userService;


  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(value = "test")
  public Mono<String> test() {
    return Mono.just("hello world");
  }

  /**
   * 查找一个
   */
  @GetMapping(value = "find")
  public Mono<User> findById(final String id) {
    return userService.getUserById(id);
  }

  /**
   * 列表
   * produces表示使用流的方式进行返回
   */
  @GetMapping(value = "list", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
  public Flux<User> findAll() {
    return userService.list().delayElements(Duration.ofSeconds(2));
  }

  /**
   * 删除
   */
  @GetMapping(value = "del")
  public Mono<User> del(final String id) {
    return userService.del(id);
  }


}
