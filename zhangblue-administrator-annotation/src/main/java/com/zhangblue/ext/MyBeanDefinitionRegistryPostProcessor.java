package com.zhangblue.ext;

import com.zhangblue.bean.Birds;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println("postProcessBeanDefinitionRegistry ... bean的数量：" + registry.getBeanDefinitionCount());
		AbstractBeanDefinition beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(Birds.class).getBeanDefinition();
		registry.registerBeanDefinition("hellow", beanDefinitionBuilder);
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("MyBeanDefinitionRegistryPostProcessor ... bean 的数量" + beanFactory.getBeanDefinitionCount());
	}
}
