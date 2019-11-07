package com.zhangblue.elasticsearch.api;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetResponse;

/**
 * @author zhangd
 */
@Slf4j
public class ElasticSearchActionListener implements ActionListener<GetResponse> {
	@Override
	public void onResponse(GetResponse documentFields) {

	}

	@Override
	public void onFailure(Exception e) {

	}
}
