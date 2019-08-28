package com.zhangblue.ext;

import com.zhangblue.bean.Birds;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(value = "com.zhangblue.ext")
@Configuration
public class ExtConfig {

	@Bean
	public Birds birds() {
		return new Birds();
	}
}
