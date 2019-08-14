package com.zhangblue.administrator.webflux.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName SSEController
 * @Description 使用服务端给客户端推送数据的方式
 * @Author zhangdi
 * @Date 2019/04/04 13:43
 **/
@RestController
@RequestMapping("/sse")
public class SSEController {

  @RequestMapping(value = "/get_data", produces = "text/event-stream;charset=UTF-8")
  public String push() {

    try {
      TimeUnit.SECONDS.sleep(1);
      //第三方数据源调用
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return "data: 返回数据：" + Math.random() + "\n\n";
  }
}
