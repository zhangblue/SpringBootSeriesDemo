package com.zhangblue.hbase.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * hbase基本操作
 *
 * @author zhangdi
 */
@Slf4j
public class HbaseBasicImplement {

  private Connection connection;

  public Connection getConnection() {
    return connection;
  }

  /**
   * 初始化hbase链接
   *
   * @throws IOException
   */
  public void initializationConnection() throws IOException {
    Path configPath = Paths.get(System.getProperty("user.dir")).resolve("config");
    Configuration configuration = new Configuration();
    configuration.addResource(
        new org.apache.hadoop.fs.Path(configPath.resolve("hbase-site.xml").toString()));
    configuration
        .addResource(new org.apache.hadoop.fs.Path(configPath.resolve("core-site.xml").toString()));
    connection = ConnectionFactory.createConnection(configuration);
  }


  public void close() throws IOException {
    connection.close();
  }

  public Table getTable(TableName tableName) throws IOException {
    return connection.getTable(tableName);
  }

  public Admin getAdmin() throws IOException {
    return connection.getAdmin();
  }

  /**
   * 查询数据
   *
   * @param tableName 表名
   * @param get       get
   */
  public Result getValue(TableName tableName, Get get) throws IOException {
    Table table = getTable(tableName);
    Result result = table.get(get);
    table.close();
    return result;
  }


  /**
   * 查询多条rowkey
   *
   * @param tableName 表名
   * @param listGet   get list
   * @return result
   */
  public Result[] getColumnValuesByRowKeys(TableName tableName, List<Get> listGet)
      throws IOException {
    Table table = getTable(tableName);
    Result[] results = table.get(listGet);
    table.close();
    return results;
  }


  /**
   * 根据删除hbase中setColumns的列
   *
   * @param tableName 表名
   * @param deletes   delete
   * @throws IOException
   */
  public void deleteByRowKey(TableName tableName, List<Delete> deletes) throws IOException {
    Table table = getTable(tableName);
    table.delete(deletes);
    table.close();
  }


  /**
   * 根据删除hbase中setColumns的列
   *
   * @param tableName 表名
   * @param delete    delete
   * @throws IOException
   */
  public void deleteByRowKey(TableName tableName, Delete delete) throws IOException {
    Table table = getTable(tableName);
    table.delete(delete);
    table.close();
  }

  /**
   * 添加数据
   *
   * @param tableName 表名
   * @param put       put
   */
  public void putDataToHbase(TableName tableName, Put put) throws IOException {
    Table table = getTable(tableName);
    table.put(put);
    table.close();
  }

  /**
   * 添加数据
   *
   * @param tableName 表名
   * @param putList   put list
   */
  public void putDataToHbase(TableName tableName, List<Put> putList)
      throws IOException {
    Table table = getTable(tableName);
    table.put(putList);
    table.close();
  }


  /**
   * scan hbase表
   *
   * @param tableName 表名
   * @param scan      scan
   * @return
   */
  public ResultScanner scanHbase(TableName tableName, Scan scan) throws IOException {
    Table table = getTable(tableName);
    ResultScanner resultsScanner = table.getScanner(scan);
    return resultsScanner;
  }


  /**
   * 创建hbase表
   *
   * @param tableName         表名
   * @param hColumnDescriptor hColumnDescriptor
   */
  public void createHbaseTable(TableName tableName,
      HColumnDescriptor hColumnDescriptor) throws IOException {
    HTableDescriptor table = new HTableDescriptor(tableName);
    Admin admin = connection.getAdmin();
    if (tableExists(tableName, admin)) {

    } else {
      table.addFamily(hColumnDescriptor);
      admin.createTable(table);
    }
  }

  /**
   * 创建hbase表
   *
   * @param tableName          表名
   * @param hColumnDescriptors hColumnDescriptors
   */
  public void createHbaseTable(TableName tableName,
      HColumnDescriptor[] hColumnDescriptors) throws IOException {
    HTableDescriptor table = new HTableDescriptor(tableName);
    Admin admin = getAdmin();
    if (tableExists(tableName, admin)) {
    } else {
      for (HColumnDescriptor hColumnDescriptor : hColumnDescriptors) {
        table.addFamily(hColumnDescriptor);
      }
      admin.createTable(table);
    }
  }

  /**
   * 检查hbase表是否存在
   *
   * @param tableName 表名
   */
  public boolean tableExists(TableName tableName) throws IOException {
    Admin admin = getAdmin();
    return tableExists(tableName, admin);
  }

  /**
   * 检查hbase表是否存在
   *
   * @param tableName 表名
   */
  public boolean tableExists(TableName tableName, Admin admin) throws IOException {
    return admin.tableExists(tableName);
  }


  /**
   * 获取表列表
   *
   * @param admin admin
   * @return
   * @throws IOException
   */
  public HTableDescriptor[] listTables(Admin admin) throws IOException {
    HTableDescriptor[] hTableDescriptors = admin.listTables();
    return hTableDescriptors;
  }
}
