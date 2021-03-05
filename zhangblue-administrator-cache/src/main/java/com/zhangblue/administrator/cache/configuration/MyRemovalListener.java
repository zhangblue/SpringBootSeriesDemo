package com.zhangblue.administrator.cache.configuration;

import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.RemovalListener;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * @author zhangdi
 * @description: RemovalListener 自定义
 * @date 2021/2/23 上午11:02
 * @since v1.0
 **/
public class MyRemovalListener implements RemovalListener<Object, Object> {


  @Override
  public void onRemoval(@Nullable Object key, @Nullable Object value,
      @NonNull RemovalCause cause) {
    System.out.println(
        cause.name() + " 自动删除了缓存 [" + key + "] --- [" + ((Integer) value).intValue() + "]");
  }
}
