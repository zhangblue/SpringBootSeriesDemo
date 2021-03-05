package com.zhangblue.rest;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangdi
 */
@SpringBootApplication
public class RestTemplateExample1 implements CommandLineRunner {

  @Autowired
  private RestTemplate restTemplate;

  public static void main(String[] args) {
    SpringApplication.run(RestTemplateExample1.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    ResponseEntity<JSONObject> forEntity = restTemplate
        .getForEntity("http://172.16.36.123:8181/h5/config/config_file_info?v=1.0",
            JSONObject.class);

    JSONObject body = forEntity.getBody();

    System.out.println(body.toJSONString());


  }
}
