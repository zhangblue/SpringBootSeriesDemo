package com.zhangblue.config;

import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

@PropertySource("classpath:test.properties")
@Configuration
public class MainConfigOfPropertyValues2 implements EmbeddedValueResolverAware {

  @Override
  public void setEmbeddedValueResolver(StringValueResolver resolver) {
    String s = resolver.resolveStringValue("${current.type}");
    System.out.println(s);
  }
}
