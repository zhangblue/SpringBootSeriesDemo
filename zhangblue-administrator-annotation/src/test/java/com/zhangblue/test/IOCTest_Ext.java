package com.zhangblue.test;

import com.zhangblue.ext.ExtConfig;
import org.junit.Test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_Ext {

  @Test
  public void test01(){
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExtConfig.class);



    applicationContext.close();
  }




}
