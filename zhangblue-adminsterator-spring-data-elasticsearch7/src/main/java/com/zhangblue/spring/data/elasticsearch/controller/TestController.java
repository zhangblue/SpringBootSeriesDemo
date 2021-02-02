package com.zhangblue.spring.data.elasticsearch.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhangblue.spring.data.elasticsearch.crud.ServiceExample;
import com.zhangblue.spring.data.elasticsearch.mapping.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangdi
 * @description: TestController
 * @date 2021/2/2 下午12:27
 * @since
 **/
@Slf4j
@RestController
@RequestMapping("/")
public class TestController {

  private ElasticsearchOperations elasticsearchOperations;
  private ServiceExample serviceExample;

  public TestController(ElasticsearchOperations elasticsearchOperations,
      ServiceExample serviceExample) {
    log.info("初始化controller 开始");
    this.elasticsearchOperations = elasticsearchOperations;
    this.serviceExample = serviceExample;
    log.info("初始化controller 结束");
  }

  @PostMapping("/alias/create")
  public boolean createAlias(@RequestParam(value = "index") String indexName,
      @RequestParam(value = "alias") String alias) {
    log.info("index = [{}], alias = [{}]", indexName, alias);
    return serviceExample.createAlias(indexName, alias);
  }

  @GetMapping("/student/{id}")
  public Student findById(@PathVariable("id") String id) {
//    Student student = elasticsearchOperations
//        .get(id.toString(), Student.class, IndexCoordinates.of("index_student"));
    Student student = serviceExample.searchData(id);
    return student;
  }

  @GetMapping("/student/search")
  public JSONObject searchData() {
    return serviceExample.criteriaQueryData("index_student");
  }

  @GetMapping("/student/random")
  public int createStudentWithRandom(@RequestParam(value = "size") int size) {
    return serviceExample.randomInsertToData(size);
  }

  @GetMapping("/student/aggr")
  public JSONObject aggrStudent() {
    return serviceExample.aggQueryData("index_student");
  }
}
