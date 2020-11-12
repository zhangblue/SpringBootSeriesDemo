package com.zhangblue.administrator.bpp.bfpp;

import java.util.Arrays;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author zhangd
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

  private BeanFactory beanFactory = null;

  @Override
  public void postProcessBeanFactory(
      ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    String[] beanDefinitionNames = configurableListableBeanFactory.getBeanDefinitionNames();

    System.out.println(Arrays.asList(beanDefinitionNames));


  }

}
