package com.zhangblue.conditional;


import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;


public class WindowsConditional implements Condition {
	/**
	 *
	 * @param context：判断条件能使用的上下文环境
	 * @param metadata：注释信息
	 * @return
	 */
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		//获得bean定义的注册类
		BeanDefinitionRegistry registry = context.getRegistry();
		String osName = context.getEnvironment().getProperty("os.name");
		//表示只有当前系统为windows，并且当前容器中有名为persion的bean时
		if (osName.contains("Windows") && registry.containsBeanDefinition("persion")) {
			return true;
		}
		else {
			return false;
		}
	}
}
