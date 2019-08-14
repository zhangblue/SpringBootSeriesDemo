package com.zhangblue.administrator.cache.configuration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * @ClassName FastJsonRedisSerializer
 * @Description TODO
 * @Author zhangdi
 * @Date 2019/04/16 17:11
 **/
public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {

  private final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
  private Class<T> clazz;

  static {
    //ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    ParserConfig.getGlobalInstance().addAccept("com.zhangblue.cache.model.User");
  }


  public FastJsonRedisSerializer(Class<T> clazz) {
    this.clazz = clazz;
  }

  @Override
  public byte[] serialize(T t) throws SerializationException {
    if (t == null) {
      return new byte[0];
    }

    return JSON.toJSONString(t).getBytes(DEFAULT_CHARSET);
  }

  @Override
  public T deserialize(byte[] bytes) throws SerializationException {
    if (bytes == null || bytes.length <= 0) {
      return null;
    }
    String str = new String(bytes, DEFAULT_CHARSET);
    return (T) JSON.parseObject(str, clazz.getClass());
  }
}
