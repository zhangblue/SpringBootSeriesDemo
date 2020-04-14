package com.zhangblue.hbase.api;

import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName HbaseOperation
 * @Description TODO
 * @Author zhangdi
 * @Date 2019/07/11 12:53
 **/
@Slf4j
public class HbaseOperation {

  private HbaseBasicImplement hbaseBasicImplement;
  private String nameSpace;


  public HbaseOperation(HbaseBasicImplement hbaseBasicImplement, String nameSpace) {
    this.hbaseBasicImplement = hbaseBasicImplement;
    this.nameSpace = nameSpace;
  }

  public HbaseBasicImplement getHbaseBasicImplement() {
    return hbaseBasicImplement;
  }

  public void cloesConnection() {
    try {
      hbaseBasicImplement.close();
    } catch (IOException e) {
      log.error("", e);
    }
  }

  /**
   * 获取hbase集群server个数
   *
   * @return
   * @throws IOException
   */
  public int getClusterServerSize() {
    int result = 0;
    try {
      result = getAdmin().getClusterStatus().getServersSize();
    } catch (IOException e) {
      log.error("", e);
    }
    return result;
  }

  /**
   * 获取tablename
   *
   * @param tableName
   * @return
   */
  public TableName getTableName(String tableName) {
    return TableName.valueOf(Bytes.toBytes(nameSpace), Bytes.toBytes(tableName));
  }

  /**
   * 获取所有rowkey
   *
   * @param tableName
   * @return
   */
  public List<String> getAllRowKeys(String tableName) {
    List<String> listResult = new ArrayList<>();
    ResultScanner results = scanHbase(tableName, new Scan());
    results.forEach(x -> listResult.add(Bytes.toString(x.getRow())));
    return listResult;
  }


  /***
   * 得到指定rowkey、family下所有的列名
   * @param tableName 表名
   * @param familyName 列族
   * @param rowKey rowkey
   * @return
   * @throws IOException
   */
  public Set<String> getColumnsByRowKey(String familyName, String rowKey, String tableName) {
    Set<String> setResult = new HashSet<>();
    Get get = new Get(Bytes.toBytes(rowKey)).addFamily(Bytes.toBytes(familyName));
    Result result = null;
    try {
      result = hbaseBasicImplement.getValue(getTableName(tableName), get);
      if (!result.isEmpty()) {
        if (result.rawCells().length > 0) {
          List<Cell> listCell = result.listCells();
          listCell.forEach(cell -> setResult
              .add(Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(),
                  cell.getQualifierLength())));
        }
      }
    } catch (IOException e) {
      log.error("", e);
    }
    return setResult;
  }

  /**
   * 获取字符串类型数据
   *
   * @param tableName
   * @param rowKey
   * @param family
   * @param column
   * @return
   * @throws IOException
   */
  public String getStringValueByRowKey(String rowKey, String family, String column,
      String tableName) {
    String value = null;
    Get get = new Get(Bytes.toBytes(rowKey))
        .addColumn(Bytes.toBytes(family), Bytes.toBytes(column));
    try {
      Result result = hbaseBasicImplement.getValue(getTableName(tableName), get);
      if (result.isEmpty() && null != result.value()) {
        value = Bytes.toString(result.value());
      }
    } catch (IOException e) {
      log.error("", e);
    }

    return value;
  }

  /**
   * 获取long类型数据
   *
   * @param tableName
   * @param rowkey
   * @param family
   * @param column
   * @return
   * @throws IOException
   */
  public long getLongValueByRowKey(String rowkey, String family, String column, String tableName) {
    long value = 0L;
    Get get = new Get(Bytes.toBytes(rowkey))
        .addColumn(Bytes.toBytes(family), Bytes.toBytes(column));
    try {
      Result result = hbaseBasicImplement.getValue(getTableName(tableName), get);
      if (result.isEmpty() && null != result.value()) {
        value = Bytes.toLong(result.value());
      }
    } catch (IOException e) {
      log.error("", e);
    }

    return value;
  }

  /**
   * 批量查询数据
   *
   * @param rowKeys   rowkey
   * @param family    列族
   * @param column    列
   * @param tableName 表名
   * @return result
   */
  public Result[] getColumnValuesByRowKeys(List<String> rowKeys, String family, String column,
      String tableName) {
    List<Get> getList = rowKeys.stream()
        .map(x -> new Get(Bytes.toBytes(x)).addColumn(Bytes.toBytes(family), Bytes.toBytes(column)))
        .collect(
            Collectors.toList());
    Result[] columnValuesByRowKeys = getColumnValuesByRowKeys(getList, tableName);
    return columnValuesByRowKeys;
  }

  /**
   * 查询多条rowkey
   *
   * @param listTables listTables
   * @param tableName  表名
   * @return result
   */
  public Result[] getColumnValuesByRowKeys(List<Get> listTables, String tableName) {
    Result[] results = new Result[listTables.size()];
    try {
      results = hbaseBasicImplement.getColumnValuesByRowKeys(getTableName(tableName), listTables);
    } catch (IOException e) {
      log.error("", e);
    }
    return results;
  }


  /**
   * 根据删除hbase中setColumns的列
   */
  public boolean deleteColumnByRowKey(String rowKey,
      String familyName, Set<String> setColumns, String tableName) {
    Delete delete = new Delete(Bytes.toBytes(rowKey));
    setColumns
        .forEach(column -> delete.addColumns(Bytes.toBytes(familyName), Bytes.toBytes(column)));
    return deleteData(tableName, delete);
  }

  /**
   * 删除某个rowkey
   */
  public boolean deleteRowKey(String tableName, String rowKey) {
    Delete delete = new Delete(Bytes.toBytes(rowKey));
    return deleteData(tableName, delete);
  }

  public boolean deleteRowKeys(String tableName, Set<String> rowKeys) {
    boolean result = false;
    List<Delete> listDelete = new ArrayList<>(rowKeys.size());
    rowKeys.forEach(rowkey -> listDelete.add(new Delete(Bytes.toBytes(rowkey))));
    try {
      hbaseBasicImplement.deleteByRowKey(getTableName(tableName), listDelete);
      result = true;
    } catch (IOException e) {
      log.error("", e);
    }
    return result;
  }

  public boolean deleteData(String tableName, Delete delete) {
    boolean result = false;
    try {
      hbaseBasicImplement.deleteByRowKey(getTableName(tableName), delete);
      result = true;
    } catch (IOException e) {
      log.error("", e);
    }
    return result;
  }


  /**
   * 添加数据
   *
   * @param rowKey    rowKey
   * @param tableName 表名
   * @param column    列名
   * @param value     值
   */
  public void putData(String rowKey, String tableName,
      String familyName,
      String[] column,
      String[] value) {
    putData(rowKey, familyName, column, value, tableName, Durability.USE_DEFAULT);
  }

  /**
   * 添加数据
   *
   * @param rowKey     rowKey
   * @param tableName  表名
   * @param durability 入库策略
   * @param column     列名
   * @param value      值
   */
  public void putData(String rowKey,
      String familyName,
      String[] column,
      String[] value, String tableName, Durability durability) {
    Put put = new Put(Bytes.toBytes(rowKey));
    for (int j = 0; j < column.length; j++) {
      put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(column[j]),
          Bytes.toBytes(value[j]));
    }
    put.setDurability(durability);
    try {
      hbaseBasicImplement.putDataToHbase(getTableName(tableName), put);
    } catch (IOException e) {
      log.error("", e);
    }
  }

  /**
   * 单条数据入库
   *
   * @param rowKey     rowkey
   * @param tableName  表名
   * @param familyName 列族
   * @param column     列名
   * @param value      值
   */
  public void putData(String rowKey, String tableName, String familyName, String column,
      String value) {
    putData(rowKey, tableName, familyName, column, value, Durability.USE_DEFAULT);
  }

  /**
   * 单条数据入库
   *
   * @param rowKey     rowkey
   * @param tableName  表名
   * @param familyName 列族
   * @param column     列名
   * @param value      值
   * @param durability 入库策略
   */
  public void putData(String rowKey, String tableName, String familyName, String column,
      String value, Durability durability) {
    Put put = new Put(Bytes.toBytes(rowKey));
    put.setDurability(durability);
    put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(column), Bytes.toBytes(value));
    try {
      hbaseBasicImplement.putDataToHbase(getTableName(tableName), put);
    } catch (IOException e) {
      log.error("", e);
    }
  }


  /**
   * 添加数据
   *
   * @param tableName 表名
   * @param putList   put列表
   */
  public void putData(String tableName, List<Put> putList) throws IOException {
    hbaseBasicImplement.putDataToHbase(getTableName(tableName), putList);
  }

  /**
   * 添加数据
   *
   * @param tableName 表名
   * @param put       put内容
   */
  public void putData(String tableName, Put put) throws IOException {
    hbaseBasicImplement.putDataToHbase(getTableName(tableName), put);
  }

  /**
   * scan数据
   *
   * @param tableName 表名
   * @return 返回的rowkey名
   */
  public ResultScanner scanTableByRowKeyRange(String tableName, String startKey, String stopKey) {
    Scan scan = new Scan(Bytes.toBytes(startKey), Bytes.toBytes(stopKey));
    return scanHbase(tableName, scan);
  }

  /**
   * 根据行键的前缀scan
   *
   * @create by renal
   */
  public ResultScanner scanHbaseByRowKeyPrefix(String tableName, String rowPrefix) {
    Scan scan = new Scan().setRowPrefixFilter(Bytes.toBytes(rowPrefix));
    return scanHbase(tableName, scan);
  }

  public ResultScanner scanHbase(String tableName, Scan scan) {
    ResultScanner results = null;
    try {
      results = hbaseBasicImplement.scanHbase(getTableName(tableName), scan);
    } catch (IOException e) {
      log.error("", e);
    }
    return results;
  }


  /**
   * 根据行键的前缀scan
   *
   * @create by renal
   */
  public ResultScanner scanHbaseByRowKeyPrefixFilter(String tableName, String rowPrefix,
      SingleColumnValueFilter filter) {
    ResultScanner resultScanner = null;
    try {
      Scan scan = new Scan();
      scan.setRowPrefixFilter(Bytes.toBytes(rowPrefix));
      scan.setFilter(filter);
      resultScanner = hbaseBasicImplement.scanHbase(getTableName(tableName), scan);
    } catch (IOException e) {
      log.error("", e);
    }
    return resultScanner;
  }


  /**
   * 创建hbase表
   *
   * @param table  表名
   * @param family 列族
   */
  public boolean createTable(String table, String family) {
    boolean result = false;
    try {
      TableName tableName = getTableName(table);
      if (hbaseBasicImplement.tableExists(tableName)) {
        result = true;
      } else {
        hbaseBasicImplement.createHbaseTable(tableName, new HColumnDescriptor(family));
        result = true;
      }
    } catch (IOException e) {
      log.error("", e);
    }
    return result;
  }

  /**
   * 创建hbase表
   *
   * @param table    表名
   * @param families 列族
   */
  public boolean createTable(String table, String[] families) {
    boolean result = false;
    try {
      TableName tableName = getTableName(table);
      if (hbaseBasicImplement.tableExists(tableName)) {
        result = true;
      } else {
        int leg = families.length;
        HColumnDescriptor[] hColumnDescriptors = new HColumnDescriptor[leg];
        for (int i = 0; i < leg; i++) {
          hColumnDescriptors[i] = new HColumnDescriptor(families[i]);
        }
        hbaseBasicImplement.createHbaseTable(tableName, hColumnDescriptors);
        result = true;
      }
    } catch (IOException e) {
      log.error("", e);
      result = false;
    }
    return result;
  }

  /***
   * 创建有数据生命周期的hbase表
   * @param table 表名
   * @param families 列族名
   * @param ttlTimes 存活时间(秒)
   * @throws Exception
   */
  public boolean createTable(String table, String[] families, int[] ttlTimes) {
    boolean flage = false;
    if (families.length == ttlTimes.length && families.length != 0) {
      try {
        TableName tableName = getTableName(table);
        if (hbaseBasicImplement.tableExists(tableName)) {
          log.info("hbase table [{}] exist!", tableName);
          flage = true;
        } else {
          HColumnDescriptor[] hColumnDescriptors = new HColumnDescriptor[families.length];
          for (int i = 0; i < families.length; i++) {
            HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(families[i]);
            if (ttlTimes[i] > 0) {
              hColumnDescriptor.setTimeToLive(ttlTimes[i]);
            }
            hColumnDescriptors[i] = hColumnDescriptor;
          }
          hbaseBasicImplement.createHbaseTable(tableName, hColumnDescriptors);
          flage = true;
        }
      } catch (IOException e) {
        log.error("", e);
      }
    }
    return flage;
  }

  /***
   * 创建有数据生命周期的hbase表
   * @param table 表名
   * @param family 列族名
   * @param ttlTimes 存活时间(秒)
   * @throws Exception
   */
  public boolean createHbaseTable(String table, String family, int ttlTimes) {
    boolean flage = false;
    try {
      TableName tableName = getTableName(table);
      if (hbaseBasicImplement.tableExists(tableName)) {
        log.info("hbase table [{}] exist!", tableName);
        flage = true;
      } else {
        HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(family).setTimeToLive(ttlTimes);
        hbaseBasicImplement.createHbaseTable(tableName, hColumnDescriptor);
        flage = true;
      }
    } catch (IOException e) {
      log.error("", e);
    }
    return flage;
  }


  /**
   * 检查hbase表是否存在
   *
   * @param tableName 表名
   */
  public boolean tableExists(String tableName) {
    boolean result = false;
    try {
      result = hbaseBasicImplement.tableExists(getTableName(tableName));
    } catch (IOException e) {
      log.error("", e);
    }
    return result;
  }

  /**
   * 检查hbase表是否存在
   *
   * @param tableName 表名
   */
  public boolean tableExists(Admin admin, String tableName) {
    boolean result = false;
    try {
      result = hbaseBasicImplement.tableExists(getTableName(tableName), admin);
    } catch (IOException e) {
      log.error("", e);
    }
    return result;
  }

  public Admin getAdmin() throws IOException {
    Admin admin = hbaseBasicImplement.getAdmin();
    return admin;
  }

  /**
   * 获取表列表
   *
   * @return
   * @throws IOException
   */
  public HTableDescriptor[] listTables() {
    HTableDescriptor[] hTableDescriptors = new HTableDescriptor[0];
    try {
      hTableDescriptors = getAdmin().listTables();
    } catch (IOException e) {
      log.error("", e);
    }
    return hTableDescriptors;
  }

  /**
   * 获取表列表
   *
   * @param admin admin
   * @return
   * @throws IOException
   */
  public HTableDescriptor[] listTables(Admin admin) {
    HTableDescriptor[] hTableDescriptors = null;
    try {
      hTableDescriptors = hbaseBasicImplement.listTables(admin);
    } catch (IOException e) {
      log.error("", e);
    }
    return hTableDescriptors;
  }

}
