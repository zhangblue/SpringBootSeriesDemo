package com.zhangblue.config;

import com.zhangblue.bean.Person;
import com.zhangblue.conditional.LinuxConditional;
import com.zhangblue.conditional.WindowsConditional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfig3 {

	@Conditional({ WindowsConditional.class })
	@Bean(value = "windowsPersion")
	public Person winPerson() {
		return new Person("windows", 10);
	}

	@Conditional({ LinuxConditional.class })
	@Bean(value = "linuxPersion")
	public Person linuxPersion() {
		return new Person("linux", 20);
	}
}
