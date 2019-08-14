package com.zhangblue.administrator.webflux.service;

import com.zhangblue.administrator.webflux.model.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author zhangdi
 * @Date 2019/04/04 11:42
 **/
@Service
public class UserService {


  public static final Map<String, User> dataMap = new HashMap<>();


  static {
    dataMap.put("1", new User("1", "小A"));
    dataMap.put("2", new User("2", "小B"));
    dataMap.put("3", new User("3", "小C"));
    dataMap.put("4", new User("4", "小D"));
    dataMap.put("5", new User("5", "小E"));
    dataMap.put("6", new User("6", "小F"));
    dataMap.put("7", new User("7", "小G"));
  }

  /**
   * 返回用户列表
   */
  public Flux<User> list() {
    return Flux.fromIterable(UserService.dataMap.values());
  }

  /**
   * 通过id查询用户
   */
  public Mono<User> getUserById(final String id) {
    return Mono.justOrEmpty(UserService.dataMap.get(id));
  }

  /**
   * 通过id删除一个用户
   */
  public Mono<User> del(final String id) {
    return Mono.justOrEmpty(UserService.dataMap.remove(id));
  }

}
