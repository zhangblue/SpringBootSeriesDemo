package com.zhangblue.administrator.cache.service;

import com.zhangblue.administrator.cache.model.Commodity;
import com.zhangblue.administrator.cache.model.SimulationDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheManager = "commodityRedisCacheManager")
public class CommodityService {

    @Qualifier(value = "commodityRedisCacheManager")
    @Autowired
    RedisCacheManager commodityRedisCacheManager;

    @Cacheable(cacheNames = "commodity")
    public Commodity getCommodityById(Integer id) {
        System.out.println("查询了订单"+id);
        Commodity commodity = SimulationDB.commodityMap.get(id + "");

        Cache commodity1 = commodityRedisCacheManager.getCache("commodity");
        commodity1.put("commodity:2","test JSON");
        return commodity;
    }
}
