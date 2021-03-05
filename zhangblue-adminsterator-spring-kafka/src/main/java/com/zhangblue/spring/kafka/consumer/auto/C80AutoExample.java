package com.zhangblue.spring.kafka.consumer.auto;

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
 * @description: 华为c80认证测试
 * @date 2021/3/1 下午3:48
 * @since v1.0
 **/
@Slf4j
@SpringBootApplication
public class C80AutoExample implements CommandLineRunner {

  public static void main(String[] args) {
    systemPropertisConfig();
    SpringApplication.run(C80AutoExample.class, args);
  }

  /**
   * 系统环境属性 --- 设置
   * <p>
   * 注:因为是系统参数，多出地方都要使用；所以直接写在启动类里面
   * <p>
   * 注:设置系统环境属性 的 方式较多，这只是其中的一种
   *
   * @author JustryDeng
   * @date 2019/2/24 10:31
   */
  private static void systemPropertisConfig() {
    System.setProperty("java.security.auth.login.config",
        "/home/ap/sds/H5/h5-kafka-auth-test/jaas.conf");
  }

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Override
  public void run(String... args) throws Exception {

    String value = getContent();
    for (int i = 0; i < 100; i++) {
      kafkaTemplate.send("h5_receiver_output", System.currentTimeMillis() + "", value);
    }
    kafkaTemplate.flush();

  }

  private String getContent() {
    String content = null;
    try {
      byte[] bytes = Files.readAllBytes(Paths.get(
          "/home/ap/sds/H5/h5-kafka-auth-test/receiver_output_demo.json"));
      content = new String(bytes, Charset.forName("UTF-8"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return content;
  }
}
