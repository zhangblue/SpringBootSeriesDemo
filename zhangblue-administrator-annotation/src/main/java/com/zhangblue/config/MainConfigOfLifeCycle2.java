package com.zhangblue.config;

import com.zhangblue.bean.Cat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfigOfLifeCycle2 {

	@Bean
	public Cat cat() {
		return new Cat();
	}
}
