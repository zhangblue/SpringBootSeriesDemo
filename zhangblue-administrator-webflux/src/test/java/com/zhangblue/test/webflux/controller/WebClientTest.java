package com.zhangblue.test.webflux.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @ClassName WebClientTest
 * @Description TODO
 * @Author zhangdi
 * @Date 2019/04/04 13:21
 **/
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {MyApplication.class})
public class WebClientTest {

  /**
   * 使用WebClient创建http get请求
   */
  @Test
  public void testBase() {
    Mono<String> bodyMono = WebClient.create().get().uri("http://localhost:8080/api/v1/user/find?id=1").accept(MediaType.APPLICATION_JSON).retrieve()
        .bodyToMono(String.class);

    System.out.println(bodyMono.block());
  }

  @Test
  public void testBase2() {
    Mono<String> bodyMono = WebClient.create().get().uri("http://localhost:8080/api/v1/user/find?id={id}", 2).accept(MediaType.APPLICATION_JSON).retrieve()
        .bodyToMono(String.class);

    System.out.println(bodyMono.block());
  }

}
