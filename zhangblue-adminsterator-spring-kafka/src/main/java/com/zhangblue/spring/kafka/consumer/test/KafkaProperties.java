package com.zhangblue.spring.kafka.consumer.test;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhangdi
 * @description: kafka 配置类
 * @date 2021/2/18 上午10:17
 * @since v1.0
 **/
@Data
@ConfigurationProperties(prefix = "test1.example")
@Component(value = "kafkaProperties")
public class KafkaProperties {

  private String brokerList;
  private String topic;
  private String groupId;

  public String getBrokerList() {
    return brokerList;
  }

  public String getTopic() {
    return topic;
  }

  public String getGroupId() {
    return groupId;
  }
}
