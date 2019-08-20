package com.zhangblue.config;

import com.zhangblue.conditional.MyImportSelector;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MyImportSelector.class})
public class MainConfig5 {
}
