package com.sohu.tv.index.data.engine.es.impl;

import com.sohu.tv.index.data.engine.es.ElasticSearchCenter;
import com.sohu.tv.index.data.engine.value.Result;
import com.sohu.tv.index.data.engine.value.ScrollParam;
import com.sohu.tv.index.data.engine.value.ScrollResult;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EsDocTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ElasticSearchCenter elasticSearchCenter;

    private final String index = "video_56_index";
    private final String type = "video_56_type";

    @Before
    public void setUp() throws Exception {
        elasticSearchCenter = new ElasticSearchCenterImpl();
    }

    /**
     * 测试添加和更新（添加和更新是同一个操作，返回IndexResponse略有不同）
     */
    @Test
    public void testAddOrUpdateDoc() {
        // 文档号
        String id = "19870209";
        // 数据（文档）
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("title", "hehe");
        dataMap.put("num", 321);
        dataMap.put("name", "呵呵3");
        dataMap.put("updateTime", new Date());
        // 插入或者更新
        Result<IndexResponse> insertResult = elasticSearchCenter.mergeDocument(index, type, id, dataMap);
        // 返回结果
        IndexResponse indexResponse = insertResult.getValue();
        logger.info("indexResult:isCreated=" + indexResponse.isCreated());
        logger.info("indexResult:version=" + indexResponse.getVersion());
    }
    
    /**
     * 获取指定文档
     */
    @Test
    public void testGetDoc(){
        String id = "19870209";
        Result<GetResponse> result = elasticSearchCenter.getSource(index, type, id);
        if (result.isSuccess()) {
            GetResponse response = result.getValue();
            Map<String, Object> map = response.getSource();
            logger.info(map.toString());
        } else {
            logger.error(result.getException().getMessage(), result.getException());
        }
    }

    /**
     * 删除指定文档
     */
    @Test
    public void testDeleteDoc() {
        // 文档号
        String id = "19870209";
        Result<DeleteResponse> deleteResult = elasticSearchCenter.deleteDocument(index, type, id);
        DeleteResponse deleteResponse = deleteResult.getValue();
        logger.info("deleteResponse:isFound=" + deleteResponse.isFound());
    }

    @Test
    public void testService() throws Exception {
        String id = "61632496";
        Result<GetResponse> result = elasticSearchCenter.getSource(index, type, id);
        GetResponse response = result.getValue();
        long t1 = System.currentTimeMillis();
        Map<String, Object> map = response.getSource();
        System.out.println("getSourceCost=" + (System.currentTimeMillis() - t1) + " ms");
        logger.info(map.toString());
        map.put("title", "新式太极-u6");
        Result<IndexResponse> updateResult = elasticSearchCenter.mergeDocument(index, type, id, map);
        IndexResponse updateResponse = updateResult.getValue();
        logger.info("updateResult:isCreated=" + updateResponse.isCreated());
        logger.info("updateResult:version=" + updateResponse.getVersion());

        Result<DeleteResponse> deleteResult = elasticSearchCenter.deleteDocument(index, type, id);
        DeleteResponse deleteResponse = deleteResult.getValue();
        logger.info("deleteResponse:isFound=" + deleteResponse.isFound());

        map.put("title", "修改title-u11");
        Result<IndexResponse> insertResult = elasticSearchCenter.mergeDocument(index, type, id, map);
        IndexResponse indexResponse = insertResult.getValue();
        logger.info("indexResult:isCreated=" + indexResponse.isCreated());
        logger.info("indexResult:version=" + indexResponse.getVersion());
    }

    @Test
    public void testGetSource() throws Exception {
        String id = "75253644";
        Result<GetResponse> result = elasticSearchCenter.getSource(index, type, id);
        if (result.isSuccess()) {
            GetResponse response = result.getValue();
            Map<String, Object> map = response.getSource();
            logger.info(map.toString());
        } else {
            logger.error(result.getException().getMessage(), result.getException());
        }
    }

    @Test
    public void testMerge() {
        String id = "61632496";
        Result<GetResponse> result = elasticSearchCenter.getSource(index, type, id);
        GetResponse response = result.getValue();
        Map<String, Object> map = response.getSource();
        logger.info(map.toString());
        map.put("title", "新式太极-u5");
        Result<IndexResponse> indexResult = elasticSearchCenter.mergeDocument(index, type, id, map);
        IndexResponse indexResponse = indexResult.getValue();
        System.out.println("indexResponse=" + indexResponse.isCreated());
        System.out.println(indexResponse.getVersion());
    }

    @Test
    public void testAggregations() {
        String index = "ugc_test";
        String type = "video_ugc_type";
        SearchResponse sr = elasticSearchCenter.getClient().prepareSearch(index)
                // .setTypes(type)
                // .setQuery(QueryBuilders.matchAllQuery())
                .addAggregation(
                        AggregationBuilders.terms("agg1").field("videoLevel").valueType(Terms.ValueType.LONG)
                )
                // .addAggregation(
                // AggregationBuilders.terms("agg2")
                // .field("cateCode")
                // )
                .execute().actionGet();

        // Get your facet results
        Terms agg1 = sr.getAggregations().get("agg1");
        logger.info("agg1.name={}", agg1.getName());
        Collection<Terms.Bucket> buckets = agg1.getBuckets();
        for (Terms.Bucket bucket : buckets) {
            Object[] params = {bucket.getKey(), bucket.getDocCount(), bucket.getAggregations().asMap()};
            logger.info("Key={},DocCount={},Aggregations={}", params);
        }
        // logger.info("agg2.name=",agg1.getName());
        // Terms agg2 = sr.getAggregations().get("agg2");
        // Collection<Terms.Bucket> buckets2 = agg2.getBuckets();
        // for(Terms.Bucket bucket : buckets2){
        // logger.info("DocCount={},KeyAsNumber={},Aggregations={}",bucket.getDocCount(),bucket.getKey(),bucket.getAggregations().asMap());
        // }
    }

    @Test
    public void testQuery() {
        String index = "ugc_test";
        String type = "video_ugc_type";
        SearchResponse response = elasticSearchCenter
                .getClient()
                .prepareSearch(index)
                .setTypes(type)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("title", "魔兽"))
                // Query
                .setPostFilter(
                        FilterBuilders.boolFilter().cache(true).must(FilterBuilders.termFilter("videoLevel", 100)))
                // .setPostFilter(FilterBuilders.rangeFilter("videoLevel").from(12).to(18))
                // // Filter
                .setFrom(0).setSize(60).setExplain(true)
                .execute()
                .actionGet();
        SearchHits searchHits = response.getHits();
        logger.info("TotalHits={}", searchHits.getTotalHits());
        for (SearchHit hit : searchHits) {
            logger.info("source={}", hit.getSource());
        }
    }

    @Test
    public void testScrollIndex() {
        String index = "sohu-ugc-refer";
        String type = "ugc_type";
        ScrollParam scrollParam = new ScrollParam(index, type, null);
        int size = 0;
        long totalTime = 0;
        while (true) {
            long t1 = System.currentTimeMillis();
            ScrollResult scrollResult = elasticSearchCenter.scrollIndex(scrollParam);
            long t2 = System.currentTimeMillis();
            totalTime += (t1 - t1);
            if (scrollResult.isSuccess()) {
                String scrollId = scrollResult.getScrollId();
                List<Map<String, Object>> list = scrollResult.getDataList();
                size += list.size();
                logger.info("list.size={} totalSize={} perCost={}ms totalCost={}ms", list.size(), size, (t2 - t1),
                        totalTime);
                scrollParam.setScrollId(scrollId);
                if (list.isEmpty()) {
                    break;
                }
            } else {
                logger.error(scrollResult.getException().getMessage(), scrollResult.getException());
                break;
            }
        }
    }

}
