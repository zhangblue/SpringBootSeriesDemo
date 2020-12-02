package com.zhangblue.administrator.cache.service;

import com.zhangblue.administrator.cache.model.Commodity;
import com.zhangblue.administrator.cache.model.SimulationDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

@Service
public class CommodityService {

  @Cacheable(cacheNames = "commodity", keyGenerator = "myKeyGenerator", cacheManager = "commodityRedisCacheManager", condition = "#id!=1", unless = "#result.id==4")
  public Commodity getCommodityById(Integer id) {
    System.out.println("查询了订单" + id);
    Commodity commodity = SimulationDB.commodityMap.get(id.toString());
    return commodity;
  }


  @CachePut(cacheNames = "commodity", key = "'getCommodityById[['+#id+']]'", cacheManager = "commodityRedisCacheManager", condition = "#id!=1", unless = "#result.id==4")
  public Commodity updateCommodityId(Integer id, String name, Integer price) {
    System.out.println("更新了数据" + id);
    if (SimulationDB.commodityMap.containsKey(id.toString())) {
      Commodity commodity = new Commodity(id, name, price);
      SimulationDB.commodityMap.put(id.toString(), commodity);
      return commodity;
    } else {
      Commodity commodity = SimulationDB.commodityMap.get(id.toString());
      commodity.setName(name).setPrice(price);
      SimulationDB.commodityMap.put(id.toString(), commodity);
      return commodity;
    }
  }

  @CacheEvict(cacheNames = "commodity", key = "'getCommodityById[['+#id+']]'", cacheManager = "commodityRedisCacheManager", condition = "#id!=1")
  public Commodity deleteCommodityId(Integer id) {
    Commodity commodity = SimulationDB.commodityMap.remove(id.toString());
    System.out.println("删除了数据" + id);
    return commodity;
  }
}
