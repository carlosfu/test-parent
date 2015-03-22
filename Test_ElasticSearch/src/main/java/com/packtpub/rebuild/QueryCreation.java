package com.packtpub.rebuild;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermFilterBuilder;

import com.sohu.tv.index.data.engine.es.ElasticSearchCenter;
import com.sohu.tv.index.data.engine.es.impl.ElasticSearchCenterImpl;

import java.io.IOException;

import static org.elasticsearch.index.query.QueryBuilders.*;
import static org.elasticsearch.index.query.FilterBuilders.*;

public class QueryCreation {
    private static ElasticSearchCenter elasticSearchCenter = new ElasticSearchCenterImpl();

    public static void main( String[] args )
    {
        String index="mytest";
        String type="mytype";
        Client client = elasticSearchCenter.getClient();
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
        for (Integer i=1; i<1000; i++){
            bulker.add(client.prepareIndex(index, type, i.toString()).setSource("text", i.toString(), "number1", i+1, "number2", i%2));
        }
        bulker.execute().actionGet();

        client.admin().indices().prepareRefresh(index).execute().actionGet();

        TermFilterBuilder filter = termFilter("number2", 1);
        RangeQueryBuilder range = rangeQuery("number1").gt(500);
        BoolQueryBuilder bool = boolQuery().must(range);

        QueryBuilder query = filteredQuery(bool, filter);

        SearchResponse response=client.prepareSearch(index).setTypes(type).setQuery(query).execute().actionGet();
        System.out.println("Matched records of elements: " + response.getHits().getTotalHits());

        elasticSearchCenter.deleteIndex(index);
    }
}
