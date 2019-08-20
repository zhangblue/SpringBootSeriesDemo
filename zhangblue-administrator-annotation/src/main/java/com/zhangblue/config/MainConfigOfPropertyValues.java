package com.zhangblue.config;

import com.zhangblue.bean.Sheep;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//使用@PropertySource读取外部配置文件中的kv，保存到运行的环境变量中
@PropertySource(value = {"classpath:/sheep.properties"})
@Configuration
public class MainConfigOfPropertyValues {
  @Bean
  public Sheep sheep() {
    return new Sheep();
  }
}
