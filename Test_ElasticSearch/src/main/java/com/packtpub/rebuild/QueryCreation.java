package com.packtpub.rebuild;

import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermFilterBuilder;
import org.elasticsearch.search.SearchHit;
import org.junit.Test;

import static org.elasticsearch.index.query.QueryBuilders.*;
import static org.elasticsearch.index.query.FilterBuilders.*;

/**
 * 简单搜索
 * @author leifu
 * @Date 2015-3-22
 * @Time 下午7:33:13
 */
public class QueryCreation extends ElasticSearchBase {

	@Test
	public void testQuery() {
		Client client = elasticSearchCenter.getClient();

		// title="title35"
		TermFilterBuilder filter = termFilter("title", "title35");
		// cid>5
		RangeQueryBuilder range = rangeQuery("cid").gt(5);
		BoolQueryBuilder bool = boolQuery().must(range);
		// 查询组合
		QueryBuilder query = filteredQuery(bool, filter);

		//搜索结果
		SearchResponse response = client.prepareSearch(INDEX).setTypes(TYPE)
				.setQuery(query).execute().actionGet();
		
		if (response.status().getStatus() == 200) {
            System.out.println("Maximum score: " + response.getHits().maxScore());
            for (SearchHit searchHit : response.getHits().getHits()) {
            	Map<String, Object> map = searchHit.getSource();
                System.out.println("hit: " + searchHit.getIndex() + ":" + searchHit.getType() + ":" + searchHit.getId());
    			System.out.println(map);
            }
    		System.out.println("Matched records of elements: " + response.getHits().getTotalHits());
        }
		
	}

}
