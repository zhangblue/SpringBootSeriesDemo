package com.zhangblue.elasticsearch.starter.configuration;

import com.zhangblue.elasticsearch.api.ElasticSearchImplement;
import com.zhangblue.elasticsearch.api.ElasticSearchOperation;
import com.zhangblue.elasticsearch.starter.properties.ElasticSearchProperties;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangd
 */
@Configuration
@EnableConfigurationProperties(ElasticSearchProperties.class)
@Slf4j
public class ElasticSearchAutoConfiguration {

	@Bean(value = "elasticSearchImplement", initMethod = "createElasticSearchConnection", destroyMethod = "closeElasticSearchConnection")
	public ElasticSearchImplement elasticSearchImplement(@Autowired ElasticSearchProperties elasticSearchProperties) {
		ElasticSearchImplement elasticSearchImplement = new ElasticSearchImplement(elasticSearchProperties.getPort(), elasticSearchProperties.getHosts());
		return elasticSearchImplement;
	}

	@Bean(value = "elasticSearchOperation")
	public ElasticSearchOperation elasticSearchOperation(@Autowired ElasticSearchImplement elasticSearchImplement) {
		ElasticSearchOperation elasticSearchOperation = new ElasticSearchOperation(elasticSearchImplement);
		return elasticSearchOperation;
	}

}
