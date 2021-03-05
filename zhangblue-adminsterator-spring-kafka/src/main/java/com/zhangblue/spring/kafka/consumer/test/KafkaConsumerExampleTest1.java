package com.zhangblue.spring.kafka.consumer.test;

import com.zhangblue.spring.kafka.consumer.normal.KafkaRecordFilterStrategy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @description: 消费者测试类
 * @date 2021/2/21 下午5:08
 * @since v1.0
 **/
@Slf4j
@SpringBootApplication
public class KafkaConsumerExampleTest1 {

  @Autowired
  private KafkaProperties kafkaProperties;

  public static void main(String[] args) {
    SpringApplication.run(KafkaConsumerExampleTest1.class, args);
  }

//  @KafkaListener(topics = "#{kafkaProperties.getTopic()}", groupId = "#{kafkaProperties.getGroupId()}", containerFactory = "consumerFactory")
  @KafkaListener(topics = "#{kafkaProperties.getTopic()}", containerFactory = "consumerFactory")
  public void listen(List<ConsumerRecord<String, String>> records) throws Exception {
    log.info("current get records size = [{}]. begin sleep!", records.size());
//    TimeUnit.SECONDS.sleep(10);
//    log.info("sleep finish!");
  }


  @Bean(name = "consumerFactory")
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    /**
     *  设置拉取等待时间
     */
    factory.getContainerProperties().setPollTimeout(1000L);
    //设置并发量，表示启动多少个 KafkaMessageListenerContainer.此值应当小于或等于topic的分区数
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
    propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBrokerList());
    propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    propsMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
    propsMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
    propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    propsMap.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 50);
    propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getGroupId());

    return propsMap;
  }

}
