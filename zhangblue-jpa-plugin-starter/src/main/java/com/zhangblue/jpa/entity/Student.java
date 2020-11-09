package com.zhangblue.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author di.zhang
 * @date 2020/5/14
 * @time 13:52
 **/
@Data
@Entity
@Table(name = "zd_student")
@EntityListeners(AuditingEntityListener.class)
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "name")
  private String name;

}
