package com.zhangblue.test;

import com.zhangblue.config.MainConfigOfPropertyValues;
import com.zhangblue.config.MainConfigOfPropertyValues2;
import com.zhangblue.ext.ExtConfig;
import org.junit.Test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class IOCTest_Ext {

  @Test
  public void test01(){
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExtConfig.class);



    applicationContext.close();
  }




}
