package com.zhangblue.administrator.cache.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SimulationDB
 * @Description TODO
 * @Author zhangdi
 * @Date 2019/04/11 15:27
 **/
public class SimulationDB {
    public static final Map<String, User> dataMap = new HashMap<>();
    public static final Map<String, Commodity> commodityMap = new HashMap<>();

    static {
        dataMap.put("1", new User(1, "小A", 10));
        dataMap.put("2", new User(2, "小B", 11));
        dataMap.put("3", new User(3, "小C", 12));
        dataMap.put("4", new User(4, "小D", 13));
        dataMap.put("5", new User(5, "小E", 14));
        dataMap.put("6", new User(6, "小F", 15));
        dataMap.put("7", new User(7, "小G", 16));
    }

    static {
        commodityMap.put("1", new Commodity(1, "商品1", 5));
        commodityMap.put("2", new Commodity(2, "商品2", 10));
        commodityMap.put("3", new Commodity(3, "商品3", 15));
        commodityMap.put("4", new Commodity(4, "商品4", 16));
    }
}
