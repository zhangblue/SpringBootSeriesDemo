package com.zhangblue.spring.data.elasticsearch.crud;

import com.alibaba.fastjson.JSONObject;
import com.zhangblue.spring.data.elasticsearch.mapping.Student;
import com.zhangblue.spring.data.elasticsearch.repository.StudentRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexedObjectInformation;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.index.AliasAction.Add;
import org.springframework.data.elasticsearch.core.index.AliasActionParameters;
import org.springframework.data.elasticsearch.core.index.AliasActions;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.BulkOptions;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
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


  public boolean createAlias(String indexName, String... aliasName) {
    log.info("create createAlias = [{}]----{}", indexName, Arrays.asList(aliasName));
    AliasActionParameters build = AliasActionParameters.builderForTemplate().withIndices(indexName)
        .withAliases(aliasName)
        .build();
    boolean alias = elasticsearchOperations.indexOps(IndexCoordinates.of(indexName))
        .alias(new AliasActions(new Add(build)));
    return alias;
  }

  public Student searchData(String id) {
    Student student = elasticsearchRestTemplate.get(id, Student.class);
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

      IndexQuery build = new IndexQueryBuilder().withObject(student).build();
      listBulk.add(build);

    }
    List<IndexedObjectInformation> index_student = elasticsearchRestTemplate
        .bulkIndex(listBulk, bulkOptions, IndexCoordinates.of("index_student"));
    return randomSize;
  }

  public JSONObject criteriaQueryData(String... indices) {
    Criteria criteria = new Criteria("s_age").is(10).and("s_name").is("zhangsan1");
    CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
    SearchHits<Student> search = elasticsearchRestTemplate
        .search(criteriaQuery, Student.class, IndexCoordinates.of(indices));

    long totalHits = search.getTotalHits();

    List<Student> collect = search.stream().map(x -> x.getContent()).collect(Collectors.toList());

    JSONObject jsonObject = new JSONObject().fluentPut("total", totalHits)
        .fluentPut("data", collect);
    return jsonObject;
  }

  public JSONObject aggQueryData(String... indices) {

    TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("test-01")
        .field("s_age");
    NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder()
        .addAggregation(termsAggregationBuilder);
    NativeSearchQuery build = nativeSearchQueryBuilder.build();

    SearchHits<Student> indexStudent = elasticsearchRestTemplate
        .search(build, Student.class, IndexCoordinates.of(indices));
    ParsedLongTerms aggregation = indexStudent.getAggregations().get("test-01");

    JSONObject jsonObject = new JSONObject();
    for (Bucket bucket : aggregation.getBuckets()) {
      jsonObject.put(bucket.getKeyAsString(), bucket.getDocCount());
    }

    return jsonObject;
  }

}
