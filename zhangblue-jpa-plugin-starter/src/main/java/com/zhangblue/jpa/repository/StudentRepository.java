package com.zhangblue.jpa.repository;

import com.zhangblue.jpa.entity.Student;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author di.zhang
 * @date 2020/5/14
 * @time 13:54
 **/
@Transactional
public interface StudentRepository extends BaseRepository<Student,Integer> {

  @Query(value = "SELECT s FROM Student AS s WHERE s.name=:myname")
  Optional<Student> findByNames(@Param("myname") String name);
}
