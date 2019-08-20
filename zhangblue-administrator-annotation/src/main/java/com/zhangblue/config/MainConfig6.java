package com.zhangblue.config;

import com.zhangblue.conditional.MyImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ MyImportBeanDefinitionRegistrar.class })
public class MainConfig6 {
}
