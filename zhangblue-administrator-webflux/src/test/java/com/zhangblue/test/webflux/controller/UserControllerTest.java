package com.zhangblue.test.webflux.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;


@AutoConfigureMockMvc
public class UserControllerTest extends BaseTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void findAll() throws Exception {
    List<String> ids = new ArrayList<>();
    ids.add("1");
    ids.add("2");

    MultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
    valueMap.put("ids", ids);
    ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/list").param("id", "1").params(valueMap).characterEncoding("utf-8"));

    MvcResult mvcResult = resultActions.andExpect(MockMvcResultMatchers.status().isOk())//表示http200的状态码
        .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(0))
        .andDo(MockMvcResultHandlers.print())
        .andReturn();
  }
}