package com.zhangblue.spring.kafka.consumer.test;

import java.util.Collection;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.common.TopicPartition;

/**
 * @author zhangdi
 * @description: 监听器
 * @date 2021/2/21 下午5:55
 * @since
 **/
public class MyConsumerRebalanceListener implements ConsumerRebalanceListener {

  @Override
  public void onPartitionsRevoked(Collection<TopicPartition> partitions) {

  }

  @Override
  public void onPartitionsAssigned(Collection<TopicPartition> partitions) {

  }
}
