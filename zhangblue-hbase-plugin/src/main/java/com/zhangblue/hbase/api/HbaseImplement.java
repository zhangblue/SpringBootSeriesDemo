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

@Slf4j
public class HbaseImplement {

  private Connection connection;

  /**
   * 初始化
   *
   * @throws IOException
   */
  public void init() throws IOException {
    Path configPath = Paths.get(System.getProperty("user.dir")).resolve("config");
    Configuration configuration = new Configuration();
    configuration.addResource(new org.apache.hadoop.fs.Path(configPath.resolve("hbase-site.xml").toString()));
    configuration.addResource(new org.apache.hadoop.fs.Path(configPath.resolve("core-site.xml").toString()));

    connection = ConnectionFactory.createConnection(configuration);
  }


  public void close() throws IOException {
    connection.close();
  }


  public TableName getTableName(String nameSpace, String tableName) {
    return TableName.valueOf(nameSpace, tableName);
  }

  public Table getTable(String nameSpace, String tableName) throws IOException {
    return connection.getTable(getTableName(nameSpace, tableName));
  }

  public Admin getAdmin() throws IOException {
    return connection.getAdmin();
  }

  /**
   * 查询数据
   *
   * @param nameSpace 命名空间
   * @param tableName 表名
   * @param get       get
   */
  public Result getValue(String nameSpace, String tableName, Get get) throws IOException {
    Table table = getTable(nameSpace, tableName);
    Result result = table.get(get);
    table.close();
    return result;
  }


  /**
   * 查询多条rowkey
   *
   * @param nameSpace 命名空间
   * @param tableName 表名
   * @param listGet   get list
   * @return result
   */
  public Result[] getColumnValuesByRowKeys(String nameSpace, String tableName, List<Get> listGet) throws IOException {
    Table table = getTable(nameSpace, tableName);
    Result[] results = table.get(listGet);
    return results;
  }


  /**
   * 根据删除hbase中setColumns的列
   *
   * @param nameSpace  命名空间
   * @param tableName  表名
   * @param listDelete delete list
   * @throws IOException
   */
  public void deleteByRowKey(String nameSpace, String tableName, List<Delete> listDelete) throws IOException {
    Table table = getTable(nameSpace, tableName);
    table.delete(listDelete);
    table.close();
  }

  /**
   * 根据删除hbase中setColumns的列
   *
   * @param nameSpace 命名空间
   * @param tableName 表名
   * @param delete    delete
   * @throws IOException
   */
  public void deleteByRowKey(String nameSpace, String tableName, Delete delete) throws IOException {
    Table table = getTable(nameSpace, tableName);
    table.delete(delete);
    table.close();
  }

  /**
   * 添加数据
   *
   * @param nameSpace 命名空间
   * @param tableName 表名
   * @param put       put
   */
  public void putDataToHbase(String nameSpace, String tableName, Put put) throws IOException {
    Table table = getTable(nameSpace, tableName);
    table.put(put);
    table.close();
  }

  /**
   * 添加数据
   *
   * @param nameSpace 命名空间
   * @param tableName 表名
   * @param putList   put list
   */
  public void putDataToHbase(String nameSpace, String tableName, List<Put> putList) throws IOException {
    Table table = getTable(nameSpace, tableName);
    table.put(putList);
    table.close();
  }


  /**
   * scan hbase表
   *
   * @param nameSpace 命名空间
   * @param tableName 表名
   * @param scan      scan
   * @return
   */
  public ResultScanner scanHbase(String nameSpace, String tableName, Scan scan) throws IOException {
    Table table = getTable(nameSpace, tableName);
    ResultScanner resultsScanner = table.getScanner(scan);
    return resultsScanner;
  }


  /**
   * 创建hbase表
   *
   * @param nameSpace         命名空间
   * @param tableName         表名
   * @param hColumnDescriptor hColumnDescriptor
   */
  public void createHbaseTable(String nameSpace, String tableName, HColumnDescriptor hColumnDescriptor) throws IOException {
    HTableDescriptor table = new HTableDescriptor(getTableName(nameSpace, nameSpace));
    Admin admin = connection.getAdmin();
    if (tableExists(nameSpace, tableName, admin)) {

    } else {
      table.addFamily(hColumnDescriptor);
      admin.createTable(table);
    }
  }

  /**
   * 创建hbase表
   *
   * @param nameSpace          命名空间
   * @param tableName          表名
   * @param hColumnDescriptors hColumnDescriptors
   */
  public void createHbaseTable(String nameSpace, String tableName, HColumnDescriptor[] hColumnDescriptors) throws IOException {
    HTableDescriptor table = new HTableDescriptor(getTableName(nameSpace, tableName));
    Admin admin = getAdmin();
    if (tableExists(nameSpace, tableName, admin)) {
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
  public boolean tableExists(String nameSpace, String tableName) throws IOException {
    Admin admin = getAdmin();
    return tableExists(nameSpace, tableName, admin);
  }

  /**
   * 检查hbase表是否存在
   *
   * @param tableName 表名
   */
  public boolean tableExists(String nameSpace, String tableName, Admin admin) throws IOException {
    return admin.tableExists(getTableName(nameSpace, tableName));
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
