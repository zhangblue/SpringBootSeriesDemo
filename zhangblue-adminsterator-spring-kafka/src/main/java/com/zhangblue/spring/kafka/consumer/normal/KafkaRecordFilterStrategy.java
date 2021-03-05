package com.zhangblue.spring.kafka.consumer.normal;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;

/**
 * @author zhangdi
 * @description: 消费者消息过滤器
 * @date 2021/2/1 下午4:52
 * @since
 **/
@Slf4j
public class KafkaRecordFilterStrategy implements RecordFilterStrategy<String, String> {


  @Override
  public boolean filter(ConsumerRecord<String, String> consumerRecord) {
    if (consumerRecord.value().length() == 1) {
      log.info("丢弃消息 [{}]", consumerRecord.value());
      return true;
    } else {
      return false;
    }
  }
}
