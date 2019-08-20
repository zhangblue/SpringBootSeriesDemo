package com.zhangblue.conditional;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;


public class LinuxConditional implements Condition {
	/**
	 *
	 * @param context：判断条件能使用的上下文环境
	 * @param metadata：注释信息
	 * @return
	 */
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String osName = context.getEnvironment().getProperty("os.name");
		if (osName.contains("Linux")) {
			return true;
		}
		else {
			return false;
		}
	}
}
