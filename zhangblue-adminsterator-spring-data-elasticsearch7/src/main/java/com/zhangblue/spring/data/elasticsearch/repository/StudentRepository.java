package com.zhangblue.spring.data.elasticsearch.repository;

import com.zhangblue.spring.data.elasticsearch.mapping.Student;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends ElasticsearchRepository<Student, String> {
 
}
