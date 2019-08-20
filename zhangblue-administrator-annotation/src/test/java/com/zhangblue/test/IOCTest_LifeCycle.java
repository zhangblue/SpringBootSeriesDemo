package com.zhangblue.test;

import com.zhangblue.config.MainConfigOfLifeCycle;
import com.zhangblue.config.MainConfigOfLifeCycle2;
import com.zhangblue.config.MainConfigOfLifeCycle3;
import com.zhangblue.config.MainConfigOfLifeCycle4;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_LifeCycle {

	@Test
	public void test01() {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
		System.out.println("容器创建完成");

		annotationConfigApplicationContext.close();
	}

	@Test
	public void test02() {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle2.class);
		System.out.println("容器创建完成");

		annotationConfigApplicationContext.close();
	}

	@Test
	public void test03(){
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle3.class);
		System.out.println("容器创建完成");

		annotationConfigApplicationContext.close();
	}

	@Test
	public void test04(){
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle4.class);
		System.out.println("容器创建完成");

		annotationConfigApplicationContext.close();
	}

}
