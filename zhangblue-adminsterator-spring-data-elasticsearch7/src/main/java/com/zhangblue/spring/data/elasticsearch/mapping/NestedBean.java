package com.zhangblue.spring.data.elasticsearch.mapping;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @author zhangdi
 * @description: nested测试
 * @date 2021/3/4 下午9:23
 * @since v1.0
 **/
@Data
public class NestedBean {

  @JSONField(name = "ip")
  private String ip;
  @JSONField(name = "city")
  private String city;//城市
  @JSONField(name = "country")
  private String country;//国家
  @Field(name = "province")
  private String province; //省
  @JSONField(name = "page")
  private String page; //页面
  @JSONField(name = "browser_id")
  private String browserId; //浏览器id
  @JSONField(name = "net_type")
  private String netType; //网络类型

}
