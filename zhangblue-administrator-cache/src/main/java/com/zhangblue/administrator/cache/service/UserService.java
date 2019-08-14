package com.zhangblue.administrator.cache.service;

import com.zhangblue.administrator.cache.model.SimulationDB;
import com.zhangblue.administrator.cache.model.User;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author zhangdi
 * @Date 2019/04/08 10:58
 **/
@Service
@CacheConfig(cacheNames = {"user"}, cacheManager = "userRedisCacheManager")
public class UserService {

  //@Cacheable(value = {"user"}, keyGenerator = "myKeyGenerator", condition = "#id>1", unless = "#a0==2")
  @Cacheable(value = {"user"})
  public User getUserById(int id) {
    System.out.println("查询了[" + id + "]号的用户");
    return SimulationDB.dataMap.get(id + "");
  }

  @CachePut(value = {"user"}, key = "#user.id")
  public User updateUser(User user) {
    System.out.println("更新用户方法调用" + user.toString());
    SimulationDB.dataMap.put(user.getId().toString(), user);
    return SimulationDB.dataMap.get(user.getId().toString());
  }


  @CacheEvict(value = {"user"}, allEntries = true, beforeInvocation = true)
  public void deleteUser(final int id) {
    System.out.println("删除用户id = " + id);
    SimulationDB.dataMap.remove(id + "");
  }


  @Caching(
      cacheable = {
          @Cacheable(value = {"user"}, key = "#name")
      },
      put = {
          @CachePut(value = {"user"}, key = "#result.id"),
          @CachePut(value = {"user"}, key = "#result.age")
      }
  )
  public User getUserByName(String name) {
    System.out.println("根据name查询user信息，user = " + name);
    Set<Map.Entry<String, User>> entries = SimulationDB.dataMap.entrySet();
    for (Map.Entry<String, User> entry : entries) {
      String key = entry.getKey();
      User value = entry.getValue();
      if (value.getName().equals(name)) {
        return value;
      }
    }
    return null;
  }
}
