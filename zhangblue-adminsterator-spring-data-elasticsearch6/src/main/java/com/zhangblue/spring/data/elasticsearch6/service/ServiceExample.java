package com.zhangblue.spring.data.elasticsearch6.service;

import com.alibaba.fastjson.JSONObject;
import com.zhangblue.spring.data.elasticsearch6.mapping.Student;
import com.zhangblue.spring.data.elasticsearch6.reposiory.StudentRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.AliasBuilder;
import org.springframework.data.elasticsearch.core.query.BulkOptions;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;


/**
 * @author zhangdi
 * @description:
 * @date 2021/2/2 下午3:25
 * @since
 **/
@Slf4j
@Service
public class ServiceExample {

  @Autowired
  private ElasticsearchRestTemplate elasticsearchRestTemplate;

  @Autowired
  private ElasticsearchOperations elasticsearchOperations;

  @Autowired
  private RestHighLevelClient restHighLevelClient;

  @Autowired
  private StudentRepository studentRepository;


  public boolean createAlias(String indexName, String aliasName) {
    log.info("create createAlias = [{}]----{}", indexName, Arrays.asList(aliasName));
    AliasBuilder aliasBuilder = new AliasBuilder().withAliasName(aliasName)
        .withIndexName(indexName);
    boolean alias = elasticsearchOperations.addAlias(aliasBuilder.build());

    return alias;
  }

  public Student getDataById(String id) {
    GetRequest index_student = new GetRequest("index_student", "test_type", id);
    Student student = null;
    try {
      GetResponse documentFields = elasticsearchRestTemplate.getClient()
          .get(index_student, RequestOptions.DEFAULT);
      student = JSONObject.parseObject(documentFields.getSourceAsString(), Student.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return student;
  }

  /**
   * 随机产生数据
   *
   * @param randomSize
   * @return
   */
  public int randomInsertToData(int randomSize) {

    BulkOptions bulkOptions = BulkOptions.builder().withTimeout(TimeValue.timeValueSeconds(10))
        .build();
    Random random = new Random();
    List<IndexQuery> listBulk = new ArrayList<>(randomSize);
    for (int i = 0; i < randomSize; i++) {
      Student student = new Student("zhangsan" + i, random.nextInt(10), new Date());
      IndexQuery build = new IndexQueryBuilder().withObject(student).withIndexName("index_student")
          .build();
      listBulk.add(build);
    }
    elasticsearchRestTemplate
        .bulkIndex(listBulk, bulkOptions);
    return randomSize;
  }

  public JSONObject searchQueryData(String... indices) {

    TermQueryBuilder age = new TermQueryBuilder("age", 10);
    TermQueryBuilder termQueryBuilder = new TermQueryBuilder("name", "zhangsan1");

    NativeSearchQuery test_type = new NativeSearchQueryBuilder()
        .withQuery(age).withTypes("test_type").withIndices(indices).build();
    List<Student> students = elasticsearchRestTemplate.queryForList(test_type, Student.class);

    long totalHits = 0;
    List<Student> collect = students.stream().collect(Collectors.toList());

    JSONObject jsonObject = new JSONObject().fluentPut("total", totalHits)
        .fluentPut("data", collect);
    return jsonObject;
  }

  public JSONObject aggQueryData(String... indices) {

    return null;
  }

}
