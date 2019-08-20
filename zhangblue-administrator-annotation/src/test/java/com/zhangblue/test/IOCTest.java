package com.zhangblue.test;

import com.zhangblue.bean.Color;
import com.zhangblue.bean.Person;
import com.zhangblue.config.*;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest {

	@Test
	public void test01(){
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
		String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println(beanDefinitionName);
		}
	}

	@Test
	public void test02(){
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
		String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println(beanDefinitionName);
		}
		Object persion = annotationConfigApplicationContext.getBean("persion");
		Object persion1 = annotationConfigApplicationContext.getBean("persion");

		System.out.println(persion==persion1);
	}

	@Test
	public void test03(){
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig3.class);
		String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanNamesForType(Person.class);
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println(beanDefinitionName);
		}
	}

	@Test
	public void test04(){
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig4.class);
		Color bean = (Color) annotationConfigApplicationContext.getBean("com.zhangblue.bean.Color");
		bean.hello();
	}

	@Test
	public void test05(){
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig5.class);
		Color bean =  annotationConfigApplicationContext.getBean(Color.class);
		bean.hello();
	}

	@Test
	public void test06(){
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig6.class);
		String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println(beanDefinitionName);
		}
	}

	@Test
	public void test07(){
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig7.class);
		Object o = annotationConfigApplicationContext.getBean("colorFactoryBean");
		System.out.println(o.getClass());
	}
}
