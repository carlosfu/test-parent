package com.yibao.hbase.demo;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;

/**
 * @author yan.zhang
 * hbase提供的数据库操作api演示demo
 */
public class HbaseUtil {
	
    private static Configuration conf;
    
	public static void open() {
		conf = HBaseConfiguration.create(); 
		conf.set("hbase.rootdir", "hdfs://192.168.1.2:9000/hbase");
		conf.set("hbase.cluster.distributed", "true");
		conf.set("hbase.master", "192.168.1.2:60000");
		conf.set("hbase.zookeeper.quorum", "192.168.1.2,192.168.1.3,192.168.1.5");
	}
	
	/**
	 * 获得集合
	 * @param tableName 表名
	 * @param family 条件列族
	 * @param column 条件列
	 * @param value 条件值
	 */
	public static Iterator list(String tableName, String family, String column, String value) {
		try {
			HbaseUtil.open();
			HTable table = new HTable(conf, tableName);
			Scan scan=new Scan();  
			scan.addColumn(family.getBytes(), column.getBytes());
			SingleColumnValueFilter filter=new SingleColumnValueFilter(  
					family.getBytes(), column.getBytes(), CompareOp.EQUAL, value.getBytes()
			);  
			scan.setFilter(filter);
			ResultScanner scanner = table.getScanner(scan);
			Iterator<Result> res = scanner.iterator();
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) throws Exception, ZooKeeperConnectionException {
		Iterator it = HbaseUtil.list("people", "info", "name", "zhangyan");
		while(it.hasNext()) {
			Result object = (Result)it.next();
			byte[] bb = object.getValue("info".getBytes(), "name".getBytes());
			String strs = new String(bb);
			System.out.println(strs);
		}
	}
	
}
