package com.zhangblue.admin.test.abstracts;

public interface FilterTest<DATA1, DATA2, T> {


  public abstract DATA2 param(DATA1 data1);


  public abstract T param2(DATA2 data2, T t);

}
