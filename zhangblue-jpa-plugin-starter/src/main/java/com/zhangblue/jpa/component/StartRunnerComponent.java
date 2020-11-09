package com.zhangblue.jpa.component;

import com.zhangblue.jpa.entity.Student;
import com.zhangblue.jpa.repository.StudentRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author di.zhang
 * @date 2020/5/14
 * @time 13:56
 **/
@Slf4j
@Component
public class StartRunnerComponent implements CommandLineRunner {

  @Autowired
  private StudentRepository studentRepository;

  @Override
  public void run(String... args) throws Exception {

//    Student student = new Student();
//    student.setName("zhangsa");
//
//    studentRepository.save(student);

    Optional<Student> zhangsa = studentRepository.findByNames("zhangsam");
    log.info("exists = [{}]",zhangsa.isPresent());
  }
}
