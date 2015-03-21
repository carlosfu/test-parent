package com.packtpub;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentFactory;

import com.sohu.tv.index.data.engine.es.ElasticSearchCenter;
import com.sohu.tv.index.data.engine.es.impl.ElasticSearchCenterImpl;

import java.io.IOException;

/**
 * 来自elasticsearch-cookbook
 * @author leifu
 * @Date 2015年2月28日
 * @Time 下午3:07:08
 */
public class BulkOperations {

    private static ElasticSearchCenter elasticSearchCenter = new ElasticSearchCenterImpl();

    public static void main( String[] args )
    {
        
        Client client = elasticSearchCenter.getClient();
        
        String index="mytest";
        String type="mytype";
        if(elasticSearchCenter.checkIndexExists(index))
            elasticSearchCenter.deleteIndex(index);

        try {
            client.admin().indices().prepareCreate(index)
                    .addMapping(type, XContentFactory.jsonBuilder()
                            .startObject()
                            .startObject(type)
                            .startObject("_timestamp").field("enabled", true).field("store", "yes").endObject()
                            .startObject("_ttl").field("enabled", true).field("store", "yes").endObject()
                            .endObject()
                            .endObject())
                    .execute().actionGet();
        } catch (IOException e) {
            System.out.println("Unable to create mapping");
        }

        BulkRequestBuilder bulker=client.prepareBulk();
        for (Integer i=1; i<=1000; i++){
            bulker.add(client.prepareIndex(index, type, i.toString()).setSource("text", i.toString()));
        }
        System.out.println("Number of actions for index: " + bulker.numberOfActions());
        bulker.execute().actionGet();

        bulker=client.prepareBulk();
        for (Integer i=1; i<=1000; i++){
            bulker.add(client.prepareUpdate(index, type, i.toString()).setScript("ctx._source.text += 2"));
        }
        System.out.println("Number of actions for udpate: " + bulker.numberOfActions());
        bulker.execute().actionGet();

        bulker=client.prepareBulk();
        for (Integer i=1; i<=1000; i++){
            bulker.add(client.prepareDelete(index, type, i.toString()));
        }
        System.out.println("Number of actions  for delete: " + bulker.numberOfActions());
        bulker.execute().actionGet();

        elasticSearchCenter.deleteIndex(index);
    }
}
