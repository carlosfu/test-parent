package com.sohu.tv.index.data.engine.es.impl;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;

import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sohu.tv.index.data.engine.es.ElasticSearchCenter;
import com.sohu.tv.index.data.engine.value.Result;

public class EsIndexTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ElasticSearchCenter elasticSearchCenter = new ElasticSearchCenterImpl();

    private final String indexName = "video_56_index";
    private final String typeName = "video_56_type";

    @Test
    public void testCrudIndex() {
        if (elasticSearchCenter.checkIndexExists(indexName)) {
            Result<DeleteIndexResponse> deleteIndexResponse = elasticSearchCenter.deleteIndex(indexName);
            logger.info("delete indexName {} success is {}", indexName, deleteIndexResponse.isSuccess());
        }

        Result<CreateIndexResponse> result = elasticSearchCenter.createIndex(indexName);
        boolean success = result.isSuccess();
        if (success) {
            logger.info("create indexName {} success is {}", indexName, result.getValue().isAcknowledged());
        } else {
            logger.info("create indexName {} error: {}", indexName, result.getException());
        }
    }

    @Test
    public void testPutMapping() {
        if (!elasticSearchCenter.checkIndexExists(indexName)) {
            Result<CreateIndexResponse> result = elasticSearchCenter.createIndex(indexName);
            logger.info("create indexName {} success is {}", indexName, result.isSuccess());
        }
        try {
            //开始和结束的startObject()、endObject()相当于json开头和结尾的{}
            XContentBuilder builder = jsonBuilder()
                    .startObject()
                    //_timestamp _ttl都是es约定
                    .startObject("_timestamp").field("enabled", true).field("store", "yes").endObject()
                    .startObject("_ttl").field("enabled", true).field("store", "yes").endObject()
                    .endObject();
            Result<PutMappingResponse> result = elasticSearchCenter.putMapping(indexName, typeName, builder);
            boolean success = result.isSuccess();
            if (success) {
                logger.info("putMapping on indexName {} success is {}", indexName, result.getValue().isAcknowledged());
            } else {
                logger.info("putMapping on indexName {} error: {}", indexName, result.getException());
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
    
    
    @Test
    public void testDeleteIndexTypeMapping(){
        elasticSearchCenter.deleteMapping(indexName, typeName);
    }

}
