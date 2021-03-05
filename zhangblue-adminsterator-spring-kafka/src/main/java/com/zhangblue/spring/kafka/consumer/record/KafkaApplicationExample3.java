package com.zhangblue.spring.kafka.consumer.record;

import java.util.HashMap;
import java.util.Map;
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

/**
 * @author zhangdi
 * @description: AckModel RECORD
 * @date 2021/2/1 下午8:07
 * @since v1.0
 **/
@Slf4j
@SpringBootApplication
public class KafkaApplicationExample3 {

  public static void main(String[] args) {
    SpringApplication.run(KafkaApplicationExample3.class, args);
  }


  /**
   * RECORD 当每一条记录被消费者监听器（ListenerConsumer）处理之后提交
   * <p>
   * 使用RECORD模式的时候，当监听器处理完消息后会自动处理，使用此模式不需要手动消费。
   *
   * @param message
   */
  @KafkaListener(containerFactory = "recordListenerContainerFactory", topics = {
      "spring-topic"}, groupId = "group-spring-kafka")
  public void onMessageManualImmediate(ConsumerRecord<String, String> message) {
    log.info("manualImmediateListenerContainerFactory 处理数据内容：{}", message.value());
  }


  @Bean("recordListenerContainerFactory")
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> manualListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(new DefaultKafkaConsumerFactory<String, String>(consumerConfigs()));
    factory.getContainerProperties().setPollTimeout(1500);
    //使用record模式不能使用batch模式
    factory.setBatchListener(false);
    //配置手动提交offset
    factory.getContainerProperties().setAckMode(AckMode.RECORD);
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
