package com.zhangblue.config;

import com.zhangblue.bean.Person;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;

//配置类==配置文件
@Configuration //告诉spring 这是一个配置类
@ComponentScans(value = {
		@ComponentScan(value = "com.zhangblue", includeFilters = {
//				@Filter(type = FilterType.ANNOTATION, classes = { Controller.class }),
//				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { BookService.class }),
				@Filter(type = FilterType.CUSTOM, classes = MyTypeFilter.class)
		}, useDefaultFilters = false)
})
public class MainConfig {

	/**
	 * 默认使用方法名作为bean的名称，如果需要单独设置，需要增加value参数
	 * @return
	 */
	@Bean(value = "persion001")
	public Person person() {
		return new Person("zhangsan", 20);
	}
}
