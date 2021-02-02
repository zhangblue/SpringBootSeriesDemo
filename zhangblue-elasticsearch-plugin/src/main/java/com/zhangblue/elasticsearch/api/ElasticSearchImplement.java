package com.zhangblue.elasticsearch.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;

import org.elasticsearch.client.RestHighLevelClient;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * elasticsearch 连接底层持久化类
 * @author zhangd
 */
@Slf4j
public class ElasticSearchImplement {

	private RestHighLevelClient restHighLevelClient;

	private int port = 9300;

	private String[] hosts = { "localhost" };

	public ElasticSearchImplement(int port, String[] hosts) {
		this.port = port;
		this.hosts = hosts;
	}

	public void createElasticSearchConnection() {
		List<HttpHost> httpHostsList = Arrays.asList(hosts).stream().map(x -> new HttpHost(x, port, "http")).collect(Collectors.toList());
		HttpHost[] httpHosts = httpHostsList.toArray(new HttpHost[httpHostsList.size()]);
		restHighLevelClient = new RestHighLevelClient(RestClient.builder(httpHosts));

	}

	public void closeElasticSearchConnection() throws IOException {
		restHighLevelClient.close();
	}


	/**
	 * 同步方式get数据
	 *
	 * @param getRequest
	 * @return
	 */
	public GetResponse getDataBySync(GetRequest getRequest) throws IOException {
		GetResponse documentFields = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
		return documentFields;
	}

	/**
	 * 异步方式get数据
	 *
	 * @param getRequest
	 * @return
	 */
	public void getDataByAsync(GetRequest getRequest, ActionListener<GetResponse> listener) {
		//restHighLevelClient.getAsync(getRequest, RequestOptions.DEFAULT, listener);
	}

	/**
	 * search数据
	 *
	 * @param searchRequest
	 * @return
	 */
	public SearchResponse searchData(SearchRequest searchRequest) throws IOException {
		SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
		return searchResponse;
	}


	public RestHighLevelClient getRestHighLevelClient() {
		return restHighLevelClient;
	}
}
