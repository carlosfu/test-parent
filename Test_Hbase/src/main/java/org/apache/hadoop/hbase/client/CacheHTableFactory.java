package org.apache.hadoop.hbase.client;

import org.apache.hadoop.conf.Configuration;

import java.io.IOException;

/**
 *
 * Created by bboniao on 6/14/14.
 */
public class CacheHTableFactory implements HTableInterfaceFactory {

    private int timeOut;

    public CacheHTableFactory(int timeOut) {
        this.timeOut = timeOut;
    }

    @Override
    public HTableInterface createHTableInterface(Configuration conf,
                                                 byte[] tableName) {
        try {
            HTable htable =  new HTable(conf, tableName);
//            htable.prewarmRegionCache(htable.getRegionsInfo());
            if (this.timeOut > 0) {
                htable.setOperationTimeout(this.timeOut);
            }
            return htable;
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    @Override
    public void releaseHTableInterface(HTableInterface table) throws IOException {
        table.close();
    }
}
