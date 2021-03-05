package com.zhangblue.admin.test.abstracts;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author zhangdi
 * @description: 解析类
 * @date 2021/2/6 下午1:19
 * @since
 **/
public class ParamClass {

  public static <T> T param(JSONObject data, String keyName, Class<T> valueType) {
    return data.getObject(keyName, valueType);
  }


  public static void main(String[] args) {
    String jsonString = "{\n"
        + "\"name\":\"zhangsan\",\n"
        + "\"age\":10,\n"
        + "\"model\":[\"a\",\"b\"]\n"
        + "}";

    JSONObject jsonObject = JSONObject.parseObject(jsonString);

    System.out.println(ParamClass.param(jsonObject,"name",String.class));
    System.out.println(ParamClass.param(jsonObject,"age",Integer.class));
    System.out.println(ParamClass.param(jsonObject,"model", JSONArray.class));
  }

}
