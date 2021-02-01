package com.zhangblue.spring.kafka.time;

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
 * @description: AckModel Time
 * <p>
 * 此模式会在监听器监听到消息后的PollTimeout的时间段内提交消费请求。
 * <p>
 * 下面配置中将PollTimeout设置的超时时间为5秒。
 * <p>
 * 使用请求http://localhost:8060//ack/sendStr/kafka-time/3发送三条消息，在消费实例接收到消息后在PollTimeout到达之前关闭消费端实例，再次启动消费实例会发现之前消费的消息会被再次读取到。
 * <p>
 * 而接收到消息PollTimeout之后关闭消费实例，则会发现消息已经被成功消费。
 * @date 2021/2/1 下午8:07
 * @since v1.0
 **/
@Slf4j
@SpringBootApplication
public class KafkaApplicationExample4 {

  public static void main(String[] args) {
    SpringApplication.run(KafkaApplicationExample4.class, args);
  }


  /**
   * TIME 当每一批poll()的数据被消费者监听器（ListenerConsumer）处理之后，距离上次提交时间大于TIME时提交
   * <p>
   * 使用RECORD模式的时候，当监听器处理完消息后会自动处理，使用此模式不需要手动消费。
   *
   * @param message
   */
  @KafkaListener(containerFactory = "timeListenerContainerFactory", topics = {
      "spring-topic"}, groupId = "group-spring-kafka")
  public void onMessageManualImmediate(ConsumerRecord<String, String> message) {
    log.info("manualImmediateListenerContainerFactory 处理数据内容：{}", message.value());
  }


  @Bean("timeListenerContainerFactory")
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> manualListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(new DefaultKafkaConsumerFactory<String, String>(consumerConfigs()));
    factory.getContainerProperties().setPollTimeout(5000);
    //使用record模式不能使用batch模式
    factory.setBatchListener(false);
    //配置手动提交offset
    factory.getContainerProperties().setAckMode(AckMode.TIME);
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
