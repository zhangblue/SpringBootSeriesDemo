package com.zhangblue.elasticsearch.api;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * elasticsearch 连接底层持久化类
 */
public class ElasticSearchImplement {

  private TransportClient client;
  private String clusterName = "elasticsearch-cluster";
  private int port = 9300;
  private String[] hosts = {"localhost"};

  public ElasticSearchImplement(String clusterName, int port, String[] hosts) {
    this.clusterName = clusterName;
    this.port = port;
    this.hosts = hosts;
  }

  public void init() throws UnknownHostException {
    Settings settings = Settings.builder().put("cluster.name", clusterName).build();
    PreBuiltTransportClient preBuiltTransportClient = new PreBuiltTransportClient(settings);
    for (String host : hosts) {
      preBuiltTransportClient.addTransportAddress(new TransportAddress(InetAddress.getByName(host), port));
    }
    client = preBuiltTransportClient;
  }

  public void closeConnect() {
    client.close();
  }

  /**
   * 获取es admin
   *
   * @return
   */
  public AdminClient getAdmin() {
    return client.admin();
  }

  /**
   * get数据
   *
   * @param getRequest
   * @return
   */
  public GetResponse getData(GetRequest getRequest) {
    GetResponse documentFields = client.get(getRequest).actionGet();
    return documentFields;
  }

  /**
   * search数据
   *
   * @param searchRequest
   * @return
   */
  public SearchResponse searchData(SearchRequest searchRequest) {
    SearchResponse searchResponse = client.search(searchRequest).actionGet();
    return searchResponse;
  }

}
