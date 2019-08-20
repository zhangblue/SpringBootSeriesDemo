package com.zhangblue.config;

import com.zhangblue.bean.Color;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ Color.class})
public class MainConfig4 {


}
