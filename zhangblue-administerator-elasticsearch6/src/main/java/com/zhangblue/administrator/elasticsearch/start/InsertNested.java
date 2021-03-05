package com.zhangblue.administrator.elasticsearch.start;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.elasticsearch.action.admin.indices.get.GetIndexResponse;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkProcessor.Listener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author zhangdi
 * @description: 插入nested数据
 * @date 2021/3/4 下午9:35
 * @since v1。0
 **/
@Component
public class InsertNested implements CommandLineRunner {

  @Override
  public void run(String... args) throws Exception {
    TransportClient transportClient = getTransportClient();
    listIndex(transportClient);
  }


  public TransportClient getTransportClient()
      throws ClassNotFoundException, NoSuchMethodException, UnknownHostException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Settings settings = Settings.builder().put("cluster.name", "bangcle_es")
        .put("client.transport.sniff", false)//5.4.0版本设置为false，不然会告警，不知道为啥
        .build();

    String[] itTransportHostName = {"172.16.9.9"};
    TransportClient transportClient = new PreBuiltTransportClient(settings);
    TransportAddress transportAddress = null;
    for (String strTransportHostName : itTransportHostName) {
      Class transportAddressClass = Class
          .forName("org.elasticsearch.common.transport.TransportAddress");
      if (transportAddressClass.isInterface()) {
        transportAddressClass = Class
            .forName("org.elasticsearch.common.transport.InetSocketTransportAddress");
      }
      Constructor<?> constructor = transportAddressClass
          .getConstructor(InetAddress.class, Integer.TYPE);
      transportAddress = (TransportAddress) constructor
          .newInstance(InetAddress.getByName(strTransportHostName), 9300);
      transportClient.addTransportAddress(transportAddress);
    }

    return transportClient;
  }


  public BulkProcessor getBulkProcessor(TransportClient transportClient) {
    BulkProcessor bulkProcessor = BulkProcessor.builder(transportClient, new Listener() {
      @Override
      public void beforeBulk(long executionId, BulkRequest request) {
        System.out.println();
      }

      @Override
      public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {

      }

      @Override
      public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
      }
    }).setBulkActions(5000)
        .setBulkSize(new ByteSizeValue(5, ByteSizeUnit.MB))
        .setFlushInterval(TimeValue.timeValueSeconds(10))
        .setConcurrentRequests(8)
        .setBackoffPolicy(
            BackoffPolicy.noBackoff()
        )
        .build();

    return bulkProcessor;
  }


  public void listIndex(TransportClient transportClient) {

    GetIndexResponse getIndexResponse = transportClient.admin().indices().prepareGetIndex()
        .execute().actionGet();

    String[] indices = getIndexResponse.getIndices();
    for (String index : indices) {
      System.out.println(index);
    }
  }
}
