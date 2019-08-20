package com.zhangblue.config;

import com.zhangblue.bean.ColorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfig7 {

	/**
	 * 此处虽然返回的是ColorFactoryBean。但其实类型为Color
	 * @return
	 */
	@Bean("colorFactoryBean")
	public ColorFactoryBean colorFactoryBean(){
		return new ColorFactoryBean();
	}
}
