package com.zhangblue.hbase.starter.tasks;

import com.zhangblue.hbase.api.HbaseOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Slf4j
public class MonitorService {

  @Autowired
  private HbaseOperation hbaseOperation;

  /**
   * 每3秒检查一次hbase状态。
   */
  @Scheduled(fixedDelay = 3000)
  public void hbaseStatusMonitor() {
    int clusterServerSize = hbaseOperation.getClusterServerSize();
    if (clusterServerSize > 0) {
      log.info("hbase cluster status = [good]");
    } else {
      log.info("hbase cluster status = [bad]");
    }
  }
}
