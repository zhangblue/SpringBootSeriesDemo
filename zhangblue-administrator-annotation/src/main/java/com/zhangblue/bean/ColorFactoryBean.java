package com.zhangblue.bean;

import org.springframework.beans.factory.FactoryBean;

//创建一个Spring定义的FactoryBean
public class ColorFactoryBean implements FactoryBean<Color> {
	//返回一个Color对象，这个对象会添加到容器中
	@Override
	public Color getObject() throws Exception {
		return new Color();
	}

	@Override
	public Class<?> getObjectType() {
		return Color.class;
	}

	/**
	 * true：表示为单实例，在容器中只会保留一份
	 * false：表示为多实例，在每次调用时创建实例
	 */
	@Override
	public boolean isSingleton() {
		return true;
	}
}
