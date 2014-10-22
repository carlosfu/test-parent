package com.carlosfu.hbase.dao;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import com.carlosfu.entity.ChannelResult;
import com.carlosfu.hbase.enums.UserRcHbaseEnum;
import com.carlosfu.hbase.util.HbaseUtil;
import com.carlosfu.mobil.enums.ArithmeticEnum;
import com.carlosfu.mobil.enums.ChannelEnum;

import java.util.*;

public class UserRcDataComponentImpl {

    /** 半天时间 */
    private static final long HALF_DAY_SECOND = 43200l;

    private static final long ukey = -100009864280797668L;

    public static void main(String[] args) {
        get(ukey, ArithmeticEnum.USER_CHASE, UserRcHbaseEnum.TABLE, null);
        
        
    }

    public static HashMap<ChannelEnum, ChannelResult> get(Long ukey, ArithmeticEnum arithmetic,
            UserRcHbaseEnum htableEnum,
            List<ChannelEnum> channelList) {

        HashMap<ChannelEnum, ChannelResult> channelResultMap = new LinkedHashMap<ChannelEnum, ChannelResult>();

        Get get = new Get(Bytes.add(Bytes.toBytes(ukey), Bytes.toBytes(arithmetic.value())));
        HTableInterface htable = null;
        try {
            get.addFamily(Bytes.toBytes(UserRcHbaseEnum.FAMILY.value()));
            // 这只获取30个版本
            get.setMaxVersions(30);
            get.setCacheBlocks(true);

            if (channelList != null && channelList.size() > 0) {
                for (ChannelEnum channel : channelList) {
                    get.addColumn(Bytes.toBytes(UserRcHbaseEnum.FAMILY.value()), Bytes.toBytes(channel.value()));
                    get.addColumn(Bytes.toBytes(UserRcHbaseEnum.FAMILY.value()), Bytes.toBytes(channel.value() + "_vv"));
                }
            }
            Result result = null;
            try {
                htable = HbaseUtil.MAJOR.getHTableNoAutoFlush(htableEnum.value());
                result = htable.get(get);
            } finally {
                if (htable != null)
                    htable.close();
            }
            List<ChannelEnum> channelEnumList = channelList == null || channelList.isEmpty() ? Arrays
                    .asList(ChannelEnum.values()) : channelList;
            for (ChannelEnum channelEnum : channelEnumList) {
                List<KeyValue> vedioList = result.getColumn(Bytes.toBytes(UserRcHbaseEnum.FAMILY.value()),
                        Bytes.toBytes(channelEnum.value()));
                if (vedioList == null || vedioList.isEmpty()) {
                    continue;
                }
                Set<String> vedioSet = new LinkedHashSet<String>();
                // 第一个版本数据的时间戳
                long firstTime = 0l;
                if (arithmetic == ArithmeticEnum.USER_CHASE) {
                    firstTime = vedioList.get(0).getTimestamp();
                }
                for (KeyValue kv : vedioList) {
                    // 追剧获取最新一天结果
                    if (arithmetic == ArithmeticEnum.USER_CHASE) {
                        if (firstTime - kv.getTimestamp() > HALF_DAY_SECOND) {
                            continue;
                        }
                    }
                    String value = Bytes.toString(kv.getValue());
                    vedioSet.add(value);
                }
                KeyValue vvKeyValue = result.getColumnLatest(Bytes.toBytes(UserRcHbaseEnum.FAMILY.value()),
                        Bytes.toBytes(channelEnum.value() + "_vv"));
                String vvStr = vvKeyValue == null ? null : Bytes.toString(vvKeyValue.getValue());
                int vv = 0;
                if (NumberUtils.isNumber(vvStr)) {
                    vv = Integer.parseInt(vvStr);
                }

                ChannelResult channelResult = new ChannelResult();
                channelResult.setVv(vv);
                channelResult.setResults(new ArrayList<String>(vedioSet));
                channelResult.setChannel(channelEnum);
                channelResultMap.put(channelEnum, channelResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("========channelResultMap: " + channelResultMap);

        return channelResultMap;
    }

}
