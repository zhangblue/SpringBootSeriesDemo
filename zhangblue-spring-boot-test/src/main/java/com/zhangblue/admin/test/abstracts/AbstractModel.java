package com.zhangblue.admin.test.abstracts;

/**
 * @author zhangdi
 * @description:
 * @date 2021/2/5 下午12:32
 * @since
 **/
public abstract class AbstractModel<DATA1, DATA2, T> implements FilterTest<DATA1, DATA2, T> {

  protected void doRun(DATA1 data1, T t) {
    DATA2 param = param(data1);
    param2(param, t);
  }

  @Override
  public DATA2 param(DATA1 data1) {

    return null;
  }
}
