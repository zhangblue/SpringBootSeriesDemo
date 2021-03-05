package com.zhangblue.spring.data.elasticsearch6.reposiory;

import com.zhangblue.spring.data.elasticsearch6.mapping.Student;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends ElasticsearchRepository<Student, String> {
 
}
