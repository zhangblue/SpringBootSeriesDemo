package com.zhangblue.config;

import com.zhangblue.bean.Person;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
public class MainConfig2 {

	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	@Lazy
	@Bean(value = "persion")
	public Person person() {
		return new Person("zhangsan", 20);
	}
}
