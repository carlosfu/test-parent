package com.sohu.tv.index.data.engine.es.impl;

import com.sohu.tv.index.data.engine.es.ElasticSearchCenter;
import com.sohu.tv.index.data.engine.es.ElasticSearchClientFactory;
import com.sohu.tv.index.data.engine.value.Result;
import com.sohu.tv.index.data.engine.value.ScrollParam;
import com.sohu.tv.index.data.engine.value.ScrollResult;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.admin.indices.close.CloseIndexResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.delete.DeleteMappingResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.admin.indices.open.OpenIndexResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Es管理(来自videoservice)
 * 
 * @author leifu
 * @Date 2015年2月28日
 * @Time 上午10:37:46
 */
public class ElasticSearchCenterImpl implements ElasticSearchCenter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Client client = ElasticSearchClientFactory.createTransportClient();

    @Override
    public boolean checkIndexExists(String name) {
        IndicesExistsResponse response = client.admin().indices().prepareExists(name).execute().actionGet();
        return response.isExists();
    }

    @Override
    public Result<CreateIndexResponse> createIndex(String name) {
        try {
            CreateIndexResponse response = client.admin().indices().prepareCreate(name).execute().actionGet();
            return new Result<CreateIndexResponse>(true, response);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<CreateIndexResponse>(false, e);
        }
    }

    @Override
    public Result<DeleteIndexResponse> deleteIndex(String name) {
        try {
            DeleteIndexResponse response = client.admin().indices().prepareDelete(name).execute().actionGet();
            return new Result<DeleteIndexResponse>(true, response);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<DeleteIndexResponse>(false, e);
        }
    }

    @Override
    public Result<CloseIndexResponse> closeIndex(String name) {
        try {
            CloseIndexResponse response = client.admin().indices().prepareClose(name).execute().actionGet();
            return new Result<CloseIndexResponse>(true, response);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<CloseIndexResponse>(false, e);
        }
    }

    @Override
    public Result<OpenIndexResponse> openIndex(String name) {
        try {
            OpenIndexResponse response = client.admin().indices().prepareOpen(name).execute().actionGet();
            return new Result<OpenIndexResponse>(true, response);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<OpenIndexResponse>(false, e);
        }
    }

    @Override
    public Result<PutMappingResponse> putMapping(String index, String typeName, XContentBuilder source) {
        try {
            PutMappingResponse response = client.admin().indices().preparePutMapping(index).setType(typeName).setSource(source).execute().actionGet();
            return new Result<PutMappingResponse>(true, response);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<PutMappingResponse>(false, e);
        }
    }

    @Override
    public Result<DeleteMappingResponse> deleteMapping(String index, String typeName) {
        try {
            DeleteMappingResponse response = client.admin().indices().prepareDeleteMapping(index).setType(typeName).execute().actionGet();
            return new Result<DeleteMappingResponse>(true, response);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<DeleteMappingResponse>(false, e);
        }
    }

    private void check(boolean isOK, String message) {
        if (!isOK) {
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public Result<IndexResponse> mergeDocument(String index, String type, String id, Map<String, Object> dataMap) {
        check(StringUtils.isNotBlank(index), "index is null");
        check(StringUtils.isNotBlank(type), "type is null");
        check(StringUtils.isNotBlank(id), "id is null");
        Result<IndexResponse> result;
        try {
            XContentBuilder xContentBuilder = jsonBuilder().startObject();
            for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
                xContentBuilder.field(entry.getKey(), entry.getValue());
            }
            xContentBuilder.endObject();
            IndexResponse response = client.prepareIndex(index, type, id).setSource(xContentBuilder).get();
            result = new Result<IndexResponse>(true, response);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new Result<IndexResponse>(false, e);
        }
        return result;
    }

    @Override
    public Result<DeleteResponse> deleteDocument(String index, String type, String id) {
        check(StringUtils.isNotBlank(index), "index is null");
        check(StringUtils.isNotBlank(type), "type is null");
        check(StringUtils.isNotBlank(id), "id is null");
        Result<DeleteResponse> result;
        try {
            DeleteResponse response = client.prepareDelete(index, type, id)
                    .execute()
                    .actionGet();
            result = new Result<DeleteResponse>(true, response);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new Result<DeleteResponse>(false, e);
        }
        return result;
    }

    @Override
    public Result<BulkResponse> bulkDocuments(String index, String type,
            LinkedHashMap<String, Map<String, Object>> idMap) {
        check(StringUtils.isNotBlank(index), "index is null");
        check(StringUtils.isNotBlank(type), "type is null");
        check(idMap != null && idMap.size() > 0, "idMap is null");
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        Result<BulkResponse> result;
        try {
            for (String id : idMap.keySet()) {
                Map<String, Object> dataMap = idMap.get(id);
                XContentBuilder xContentBuilder = jsonBuilder().startObject();
                for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
                    xContentBuilder.field(entry.getKey(), entry.getValue());
                }
                xContentBuilder.endObject();
                IndexRequestBuilder indexRequestBuilder = client.prepareIndex(index, type, id).setSource(
                        xContentBuilder);
                bulkRequest.add(indexRequestBuilder);
            }
            BulkResponse bulkResponse = bulkRequest.execute().actionGet();
            result = new Result<BulkResponse>(true, bulkResponse);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new Result<BulkResponse>(false, e);
        }
        return result;
    }

    @Override
    public ScrollResult scrollIndex(ScrollParam scrollParam) {
        ScrollResult result = null;
        SearchResponse scrollResponse = null;
        try {
            String scrollId = scrollParam.getScrollId();
            if (StringUtils.isBlank(scrollId)) {
                scrollResponse = client.prepareSearch(scrollParam.getIndex())
                        .setTypes(scrollParam.getType())
                        .setSearchType(SearchType.SCAN)
                        .setScroll(scrollParam.getTimeValue())
                        .setSize(scrollParam.getSize()).execute().actionGet();
                // 第一次查询，只返回数量和一个scrollId
                logger.warn("firstScroll:TotalHits=" + scrollResponse.getHits().getTotalHits());
                scrollId = scrollResponse.getScrollId();
            }
            // 使用第一次访问的scrollId继续访问
            scrollResponse = client.prepareSearchScroll(scrollId)
                    .setScroll(scrollParam.getTimeValue())
                    .execute().actionGet();
            List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
            for (SearchHit hit : scrollResponse.getHits()) {
                Map<String, Object> map = hit.getSource();
                dataList.add(map);
            }
            if (scrollResponse.getHits().hits().length == 0) {
                logger.warn("scrollHitLength=0 scroll Done");
            }
            result = new ScrollResult(true, scrollResponse.getScrollId(), dataList, scrollResponse);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new ScrollResult(false, e);
        }
        return result;
    }

    @Override
    public Result<GetResponse> getSource(String index, String type, String id) {
        check(StringUtils.isNotBlank(index), "index is null");
        check(StringUtils.isNotBlank(type), "type is null");
        check(StringUtils.isNotBlank(id), "id is null");
        Result<GetResponse> result;
        try {
            GetResponse response = client.prepareGet(index, type, id)
                    .execute()
                    .actionGet();
            result = new Result<GetResponse>(true, response);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new Result<GetResponse>(false, e);
        }
        return result;
    }

    @Override
    public Client getClient() {
        return client;
    }

}
