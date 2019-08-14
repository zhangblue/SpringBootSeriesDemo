package com.zhangblue.test.cache;

import com.zhangblue.administrator.cache.CacheApplication;
import com.zhangblue.administrator.cache.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName Test
 * @Description TODO
 * @Author zhangdi
 * @Date 2019/04/16 15:59
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CacheApplication.class)
public class TestRedis {

  @Autowired
  private RedisTemplate<Object, User> userRedisTemplate;

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  @Test
  public void test01() {
    User zhangsan = new User(20, "zhangsan", 15);
    userRedisTemplate.opsForValue().set("user-01", zhangsan);
  }

  @Test
  public void test02() {
    stringRedisTemplate.opsForValue().set("aaaaa", "bbbb");
  }
}
