package com.zhangblue.spring.kafka.consumer.batch;

import lombok.Data;

/**
 * @author zhangdi
 * @description: kafka 配置类
 * @date 2021/2/18 上午10:17
 * @since v1.0
 **/
@Data
public class KafkaProperties {

  private String topic = "spring-topic";


}
