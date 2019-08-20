package com.zhangblue.config;

import com.zhangblue.aop.LogAspects;
import com.zhangblue.aop.MathCaluclator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@Configuration
public class MainConfigAOP {

	@Bean
	public MathCaluclator caluclator() {
		return new MathCaluclator();
	}

	@Bean
	public LogAspects logAspects() {
		return new LogAspects();
	}
}
