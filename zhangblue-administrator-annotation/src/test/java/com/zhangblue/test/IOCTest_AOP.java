package com.zhangblue.test;

import com.zhangblue.aop.MathCaluclator;
import com.zhangblue.config.MainConfigAOP;
import com.zhangblue.config.MainConfigOfPropertyValues;
import com.zhangblue.config.MainConfigOfPropertyValues2;
import org.junit.Test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_AOP {

  @Test
  public void test01() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigAOP.class);
		MathCaluclator bean = applicationContext.getBean(MathCaluclator.class);
		bean.div(4, 0);
	}
}
