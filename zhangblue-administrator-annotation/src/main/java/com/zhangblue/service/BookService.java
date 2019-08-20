package com.zhangblue.service;

import com.zhangblue.bean.Sheep;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class BookService {
  @Inject
  private Sheep sheep;
}
