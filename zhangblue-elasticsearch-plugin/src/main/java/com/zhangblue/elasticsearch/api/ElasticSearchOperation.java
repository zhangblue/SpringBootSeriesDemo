package com.zhangblue.elasticsearch.api;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.get.GetIndexResponse;
import org.elasticsearch.client.AdminClient;

/**
 * elasticsearch 操作封装类
 */
@Slf4j
public class ElasticSearchOperation {

	private ElasticSearchImplement elasticSearchImplement;

	public ElasticSearchOperation(ElasticSearchImplement elasticSearchImplement) {
		this.elasticSearchImplement = elasticSearchImplement;
	}

	/**
	 * 得到index列表
	 * @return
	 */
	public String[] listIndices() {
		AdminClient admin = elasticSearchImplement.getAdmin();
		GetIndexResponse getIndexResponse = admin.indices().prepareGetIndex().get();
		return getIndexResponse.getIndices();
	}

}
