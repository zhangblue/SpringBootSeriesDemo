package com.zhangblue.test;

import com.zhangblue.config.MainConfigOfPropertyValues;
import com.zhangblue.config.MainConfigOfPropertyValues2;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_PropertyValue {

  @Test
  public void test01(){
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);


    String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();

    for (String beanDefinitionName : beanDefinitionNames) {
      System.out.println(beanDefinitionName);
    }
  }

  @Test
  public void test02(){
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfPropertyValues2.class);

    MainConfigOfPropertyValues2 bean = applicationContext.getBean(MainConfigOfPropertyValues2.class);

    applicationContext.close();

  }


}
