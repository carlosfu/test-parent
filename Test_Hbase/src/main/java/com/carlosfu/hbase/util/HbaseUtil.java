package com.carlosfu.hbase.util;

import static org.apache.hadoop.hbase.util.Bytes.toBigDecimal;
import static org.apache.hadoop.hbase.util.Bytes.toBoolean;
import static org.apache.hadoop.hbase.util.Bytes.toDouble;
import static org.apache.hadoop.hbase.util.Bytes.toFloat;
import static org.apache.hadoop.hbase.util.Bytes.toInt;
import static org.apache.hadoop.hbase.util.Bytes.toLong;
import static org.apache.hadoop.hbase.util.Bytes.toShort;

import java.io.IOException;
import java.math.BigDecimal;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.RegionSplitter.MD5StringSplit;
import org.apache.hadoop.hbase.util.RegionSplitter.SplitAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 提供HTable和HbaseAdmin(来自投放)
 * 
 * @author leifu
 * @Date 2014年10月22日
 * @Time 下午5:34:01
 */
public enum HbaseUtil {
	MAJOR(HbaseUtil.MAJOR_HBASE_SITE, 0), MAP(HbaseUtil.MAP_HBASE_SITE, 0), TEST(
			HbaseUtil.TEST_HBASE_SITE, 0);

	public static final String MAJOR_HBASE_SITE = "major-hbase-site.xml";
	public static final String MAP_HBASE_SITE = "map-hbase-site.xml";
	public static final String TEST_HBASE_SITE = "hbase-site-test.xml";

	private static final int DEFAULT_WRITE_BUFFER_SIZE = 10485760;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/** 配置对象 */
	private Configuration conf;

	/** HTable池子 */
	private HTablePool hpool;

	/**
	 * 构造方法，初始字段，除了admin。因为会抛异常HBaseAdmin
	 */
	private HbaseUtil(String configPath, int timeOut) {
		this.conf = HBaseConfiguration.create();
		this.conf.addResource(configPath);
		this.hpool = new HTablePool(this.conf, Integer.MAX_VALUE,
				new CacheHTableFactory(timeOut), null);
	}

	/**
	 * 从池子中获得HTable
	 * 
	 * @param tableName
	 *            表名
	 * @param autoFlush
	 *            自动刷新
	 * @throws IOException
	 */
	private HTableInterface getHTables(String tableName, boolean autoFlush)
			throws IOException {
		HTableInterface table = this.hpool.getTable(tableName);
		if (!autoFlush) {
			HTable ht = (HTable) table;
			ht.setAutoFlush(false);
			ht.setWriteBufferSize(DEFAULT_WRITE_BUFFER_SIZE);
			return ht;
		}
		return table;
	}

	/**
	 * 获得HTable，使用完毕需要调用close()关闭。插入数据的时候会自动刷新
	 * 
	 * @param tableName
	 *            表名
	 * @throws IOException
	 */
	public HTableInterface getHTableAutoFlush(String tableName)
			throws IOException {
		return getHTables(tableName, true);
	}

	/**
	 * 获得HTable，使用完毕需要调用close()关闭。插入数据的时候不会自动刷新,
	 * 必须在所有插入数据完成后调用flushCommint()方法刷新
	 * 
	 * @param tableName
	 *            表名
	 * @throws IOException
	 */
	public HTableInterface getHTableNoAutoFlush(String tableName)
			throws IOException {
		return getHTables(tableName, false);
	}

	/**
	 * 字节转换成指定类型
	 * 
	 */
	public Object toType(byte[] obj, Class<?> clazz) {
		if (obj == null || obj.length == 0) {
			return null;
		}
		if (clazz == Boolean.class || clazz == boolean.class) {
			return toBoolean(obj);
		} else if (clazz == Double.class || clazz == double.class) {
			return toDouble(obj);
		} else if (clazz == Float.class || clazz == float.class) {
			return toFloat(obj);
		} else if (clazz == Integer.class || clazz == int.class) {
			return toInt(obj);
		} else if (clazz == String.class) {
			return Bytes.toString(obj);
		} else if (clazz == BigDecimal.class) {
			return toBigDecimal(obj);
		} else if (clazz == Long.class || clazz == long.class) {
			return toLong(obj);
		} else if (clazz == Short.class || clazz == short.class) {
			return toShort(obj);
		}
		throw new IllegalArgumentException("type no support!");
	}

	public byte[][] getMd5Split(int n) {
		SplitAlgorithm split = new MD5StringSplit();
		return split.split(n);
	}

	/**
	 * 均衡的生成region
	 * 
	 * public byte[][] getUniformSplit(int n) { SplitAlgorithm split = new
	 * UniformSplit(); return split.split(n); }
	 */

	/**
	 * Hbase管理类，每次重新创建，使用完必须关闭，调用close()
	 * 
	 */
	public HBaseAdmin getAdmin() {
		try {
			return new HBaseAdmin(conf);
		} catch (MasterNotRunningException e) {
			logger.error(e.getMessage(), e);
		} catch (ZooKeeperConnectionException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public Configuration getConf() {
		return conf;
	}
}
