package com.zhangblue.conditional;

import com.zhangblue.bean.Color;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
	/**
	 * 把所有的需要添加到容器中
	 * @param importingClassMetadata 当前类的注解信息
	 * @param registry bean定义的注册类
	 */
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		//指定bean的定义信息
		RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Color.class);
		//注册一个bean，指定bean名称
		registry.registerBeanDefinition("color",rootBeanDefinition);
	}
}
