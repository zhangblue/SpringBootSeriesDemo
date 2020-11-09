package com.zhangblue.jpa.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


/**
 * author email: yongjin.pan@bangcle.com version time: 上午9:55 17-10-23.
 */
@NoRepositoryBean
public interface BaseRepository<T, I extends Serializable> extends JpaRepository<T, Serializable> {

}
