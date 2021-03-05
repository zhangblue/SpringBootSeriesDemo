package com.zhangblue.spring.kafka.consumer.manual;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties.AckMode;
import org.springframework.kafka.support.Acknowledgment;

/**
 * @author zhangdi
 * @description: AckModel MANUAL 消费模式参见：https://blog.csdn.net/qq330983778/article/details/105937689/
 * @date 2021/2/1 下午8:07
 * @since v1.0
 **/
@Slf4j
@SpringBootApplication
public class KafkaApplicationExample2 {

  public static void main(String[] args) {
    SpringApplication.run(KafkaApplicationExample2.class, args);
  }


  /**
   * MANUAL_IMMEDIATE 手动调用Acknowledgment.acknowledge()后立即提交
   *
   * @param message
   * @param ack
   */
  @KafkaListener(containerFactory = "manualListenerContainerFactory", topics = {
      "spring-topic"}, groupId = "group-spring-kafka")
  public void onMessageManualImmediate(List<ConsumerRecord<String, String>> message,
      Acknowledgment ack) {
    log.info("manualImmediateListenerContainerFactory 处理数据量：{}", message.size());
    message.forEach(
        item -> log.info("manualImmediateListenerContainerFactory 处理数据内容：{}", item.value()));
    try {
      TimeUnit.HOURS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    ack.acknowledge();//直接提交offset
  }


  @Bean("manualListenerContainerFactory")
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> manualListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(new DefaultKafkaConsumerFactory<String, String>(consumerConfigs()));
    factory.getContainerProperties().setPollTimeout(1500);
    factory.setBatchListener(true);
    //配置手动提交offset
    factory.getContainerProperties().setAckMode(AckMode.MANUAL);
    return factory;
  }

  private Map<String, Object> consumerConfigs() {
    Map<String, Object> propsMap = new HashMap<>();
    propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.16.36.123:9092");
    propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    propsMap.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 2);
    return propsMap;
  }
}
