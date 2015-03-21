package com.sohu.tv.index.data.engine.es;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author leifu
 * @Date 2015年2月28日
 * @Time 上午9:59:29
 */
public class ElasticSearchClientFactoryTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testCreateTransportClient() throws Exception {
        Client client = ElasticSearchClientFactory.createTransportClient();
        long start = System.currentTimeMillis();
        GetResponse response = client.prepareGet("video_56_index", "video_56_type", "75253644")
                .execute()
                .actionGet();
        System.out.println("cost=" + (System.currentTimeMillis() - start) +" ms");
        System.out.println(response.isExists());
        System.out.println(response.getSource());
        client.close();
    }
}
