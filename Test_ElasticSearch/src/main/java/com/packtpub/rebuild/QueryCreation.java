package com.packtpub.rebuild;

import java.util.Map;

import org.elasticsearch.action.count.CountResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermFilterBuilder;
import org.elasticsearch.search.SearchHit;
import org.junit.Test;

import static org.elasticsearch.index.query.QueryBuilders.*;
import static org.elasticsearch.index.query.FilterBuilders.*;

/**
 * 简单搜索
 * 
 * @author leifu
 * @Date 2015-3-22
 * @Time 下午7:33:13
 */
public class QueryCreation extends ElasticSearchBase {

    @Test
    public void testQuery() {
        Client client = elasticSearchCenter.getClient();

//        QueryBuilder query = QueryBuilders.boolQuery()
//                .must(QueryBuilders.rangeQuery("cid").gte(5))
//                .must(QueryBuilders.termQuery("title", "title35"));
        QueryBuilder query = QueryBuilders.boolQuery().must(QueryBuilders.rangeQuery("cid").gte(5));

        // 搜索个数
        CountResponse countResponse = client.prepareCount(INDEX).setTypes(TYPE).setQuery(query).execute().actionGet();
        System.out.println("count: " + countResponse.getCount());

        // 搜索结果
        SearchResponse response = client.prepareSearch(INDEX).setTypes(TYPE).setQuery(query).setSize(50).execute().actionGet();

        if (response.status().getStatus() == 200) {
            System.out.println("Maximum score: " + response.getHits().maxScore());
            for (SearchHit searchHit : response.getHits().getHits()) {
                Map<String, Object> map = searchHit.getSource();
//                System.out.println("hit: " + searchHit.getIndex() + ":" + searchHit.getType() + ":" + searchHit.getId());
                System.out.println(map);
            }
            System.out.println("Matched records of elements: " + response.getHits().getTotalHits() + ", total size: " + response.getHits().getHits().length);
        }

    }

}
