package com.zhangblue.spring.kafka.producer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author zhangdi
 * @description: kafka 生产者
 * @date 2021/2/20 下午7:30
 * @since v1.0
 **/
@Slf4j
@SpringBootApplication
public class KafkaProducerApplicationExample1 implements CommandLineRunner {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;


  public static void main(String[] args) {
    SpringApplication.run(KafkaProducerApplicationExample1.class, args);
  }


  @Override
  public void run(String... args) throws Exception {
    String value = getContent();
    log.info(value);

    for (int i = 0; i < 1; i++) {
      kafkaTemplate.send("spring-topic", System.currentTimeMillis() + "", value);
    }
    kafkaTemplate.flush();
  }

  private String getContent() {
    String content = null;
    try {
      byte[] bytes = Files.readAllBytes(Paths.get(
          "/Users/zhangdi/work/workspace/bangcle-git/h5-safety-detection/data_demo/receiver_output_demo.json"));
      content = new String(bytes, Charset.forName("UTF-8"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return content;
  }

}
