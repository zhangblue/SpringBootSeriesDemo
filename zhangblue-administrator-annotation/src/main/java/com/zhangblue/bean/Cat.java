package com.zhangblue.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Cat implements InitializingBean, DisposableBean {

	public Cat() {
		System.out.println("cat constructor ....");
	}

	//执行销毁方法
	@Override
	public void destroy() throws Exception {
		System.out.println("cat destory ......");
	}

	//在参数设置完成后，执行初始化方法
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("cat init .....");
	}
}
