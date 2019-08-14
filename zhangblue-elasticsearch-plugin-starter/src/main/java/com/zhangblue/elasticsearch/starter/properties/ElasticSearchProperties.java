package com.zhangblue.elasticsearch.starter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "zhangblue.elasticsearch")
@Data
public class ElasticSearchProperties {
	private int port = 9300;
	private String clusterName;
	private String[] hosts;



}
