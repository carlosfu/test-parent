package com.carlosfu.hbase.dao;

import com.carlosfu.hbase.enums.VideoRcHbaseEnum;
import com.carlosfu.hbase.util.HbaseUtil;
import com.carlosfu.mobil.enums.ArithmeticEnum;
import com.carlosfu.mobil.enums.ChannelEnum;
import com.carlosfu.mobil.enums.VideoTypeEnum;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * hbase简易dao
 * @author leifu
 * @Time 2014年10月13日
 */
public class HbaseDao {
    protected static final String videoTableName = VideoRcHbaseEnum.TABLE.value();
//    protected final String userTableName = UserRcHbaseEnum.TABLE.value();
    protected static final String hbaseFamily = VideoRcHbaseEnum.FAMILY.value();

    /**
     * 根据table-键值-列簇，查询数据
     * @param rowkey
     * @param channelValue
     * @param tableName
     * @return
     * @throws IOException
     */
    public static List<String> queryData(String rowkey, String channelValue, String tableName) throws IOException {
        HTableInterface htable = HbaseUtil.MAJOR.getHTableNoAutoFlush(tableName);
        System.out.println(rowkey);
        Get get = new Get(Bytes.toBytes(rowkey));
        get.addFamily(Bytes.toBytes(hbaseFamily));
        get.setCacheBlocks(true);
        get.setMaxVersions(60);

        Result result = htable.get(get);
        System.out.println("result: " + result);
        List<KeyValue> kvList = result.getColumn(Bytes.toBytes(hbaseFamily),
                Bytes.toBytes(channelValue));
        System.out.println("kvList: " + kvList);
        List<String> vidList = new ArrayList<String>();
        for (KeyValue kv : kvList) {
            vidList.add(Bytes.toString(kv.getValue()));
            System.out.println("result=" + Bytes.toString(kv.getValue()));
        }
        htable.close();
        return vidList;
    }

    /**
     * 导入
     * @param rowkey
     * @param li
     * @param channelValue
     * @param tableName
     * @throws IOException
     */
    public static void batchInsertData(String rowkey, String[] li, String channelValue, String tableName) throws IOException {
        HTableInterface htable = HbaseUtil.MAJOR.getHTableNoAutoFlush(tableName);
        System.out.println(rowkey);
        Put put = new Put(Bytes.toBytes(rowkey));
        int i = 0;
        for (String s : li) {
            put.add(Bytes.toBytes(hbaseFamily), Bytes.toBytes(channelValue), Integer.MAX_VALUE - i,
                    Bytes.toBytes(s));
            i++;
        }
        htable.put(put);
        htable.flushCommits();
        htable.close();
    }

    /**
     * 基于视频推荐结果
     * @param pid
     * @param arithmeticEnum
     * @param videoTypeEnum
     * @return
     */
    protected static String generateRowKey(String pid, ArithmeticEnum arithmeticEnum, VideoTypeEnum videoTypeEnum) {
        String rowkey = DigestUtils.md5Hex(pid) + "|" + videoTypeEnum.value() + "|" + pid + "|"
                + arithmeticEnum.value();
        return rowkey;
    }
    
    public static void main(String[] args) throws IOException {
        //亚运会视频
        String pid = "7140088";
        String rowKey = generateRowKey(pid, ArithmeticEnum.SPORTS_SMALL_MATRIX, VideoTypeEnum.P_ID);
        queryData(rowKey, ChannelEnum.SPORTS_CATECODE.value(), videoTableName);
    }
    

}
