package com.zhangblue.spring.kafka.count;

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
 * 此模式会在监听器监听到消息等于AckCount的数量提交消费请求。
 * <p>
 * 上面配置中将`AckCount``设置为5条消息。
 * <p>
 * 使用请求http://localhost:8060//ack/sendStr/kafka-count/9向队列发送9条数据，然后关闭消费端实例，然后再次启动时发现最后4条消息再次被读取到。
 * <p>
 * 此时前5条数据被消费，最后四条消息需要凑齐5条数据后才能消费。
 * @date 2021/2/1 下午8:07
 * @since v1.0
 **/
@Slf4j
@SpringBootApplication
public class KafkaApplicationExample5 {

  public static void main(String[] args) {
    SpringApplication.run(KafkaApplicationExample5.class, args);
  }


  /**
   * COUNT 当每一批poll()的数据被消费者监听器（ListenerConsumer）处理之后，被处理record数量大于等于COUNT时提交
   *
   * @param message
   */
  @KafkaListener(containerFactory = "countListenerContainerFactory", topics = {
      "spring-topic"}, groupId = "group-spring-kafka")
  public void onMessageManualImmediate(ConsumerRecord<String, String> message) {
    log.info("manualImmediateListenerContainerFactory 处理数据内容：{}", message.value());
  }


  @Bean("countListenerContainerFactory")
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> manualListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(new DefaultKafkaConsumerFactory<String, String>(consumerConfigs()));
    factory.getContainerProperties().setPollTimeout(1500);
    //使用record模式不能使用batch模式
    factory.setBatchListener(false);
    //配置手动提交offset
    factory.getContainerProperties().setAckMode(AckMode.COUNT);
    factory.getContainerProperties().setAckCount(5);
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
