package com.zhangblue.spring.kafka.consumer.batch;

import com.zhangblue.spring.kafka.consumer.normal.KafkaRecordFilterStrategy;
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

/**
 * @author zhangdi
 * @description: spring-kafka
 * @date 2021/2/1 下午4:03
 * @since
 **/
@Slf4j
@SpringBootApplication
public class KafkaApplicationExampleBatch {

  public static void main(String[] args) {
    SpringApplication.run(KafkaApplicationExampleBatch.class, args);
  }

  @KafkaListener(topics = "spring-topic", groupId = "group-spring-kafka", containerFactory = "consumerFactory")
  public void listen(List<ConsumerRecord<String, String>> records) throws Exception {

//    log.info("[{}] === [{}] === [{}]", record.partition(), record.key(), record.value());
    log.info("current get records size = [{}]. begin sleep!", records.size());
    TimeUnit.SECONDS.sleep(10);
    log.info("sleep finish!");
//    ack.acknowledge();
  }


  @Bean(name = "consumerFactory")
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();

    /**
     *  设置拉取等待时间
     */
    factory.getContainerProperties().setPollTimeout(5000L);
    //设置并发量，小于或等于Topic的分区数,并且要在consumerFactory设置一次拉取的数量
    factory.setConcurrency(1);
    //设置为批量j监听
    factory.setBatchListener(true);
    //指定使用此bean工厂的监听方法，消费确认为方式为用户指定aks,可以用下面的配置也可以直接使用enableAutoCommit参数
    factory.getContainerProperties().setAckMode(AckMode.BATCH);
    //使用过滤器
    factory.setAckDiscarded(true);
    factory.setRecordFilterStrategy(new KafkaRecordFilterStrategy());

    factory.setConsumerFactory(new DefaultKafkaConsumerFactory<String, String>(consumerConfigs()));
    return factory;
  }

  private Map<String, Object> consumerConfigs() {
    Map<String, Object> propsMap = new HashMap<>();
    propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.16.36.123:9092");
    propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
    propsMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
    propsMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
    propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    propsMap.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 50);

    return propsMap;
  }

}
