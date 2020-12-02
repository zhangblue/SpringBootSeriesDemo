package com.zhangblue.administrator.cache.controller;

import com.zhangblue.administrator.cache.model.Commodity;
import com.zhangblue.administrator.cache.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controller/commodity")
public class CommodityController {

  @Autowired
  private CommodityService commodityService;

  @GetMapping(value = "/get")
  public Commodity getCommodity(Integer id) {
    Commodity commodityById = commodityService.getCommodityById(id);
    return commodityById;
  }

  @GetMapping(value = "/update")
  public Commodity updateCommodity(Integer id, String name, Integer prise) {
    Commodity commodityById = commodityService.updateCommodityId(id, name, prise);
    return commodityById;
  }

  @GetMapping(value = "/del")
  public Commodity updateCommodity(Integer id) {
    Commodity commodityById = commodityService.deleteCommodityId(id);
    return commodityById;
  }

}
