package com.zhangblue.elasticsearch.api;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;


/**
 * elasticsearch 操作封装类
 * @author zhangd
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
	public String[] listIndices() throws IOException {
		GetIndexRequest request = new GetIndexRequest("*");
		request.includeDefaults(true);
		GetIndexResponse getIndexResponse = elasticSearchImplement.getRestHighLevelClient().indices().get(request, RequestOptions.DEFAULT);
		return getIndexResponse.getIndices();
	}

}
