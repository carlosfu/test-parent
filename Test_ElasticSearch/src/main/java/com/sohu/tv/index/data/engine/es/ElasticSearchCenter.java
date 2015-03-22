package com.sohu.tv.index.data.engine.es;

import com.sohu.tv.index.data.engine.value.Result;
import com.sohu.tv.index.data.engine.value.ScrollParam;
import com.sohu.tv.index.data.engine.value.ScrollResult;

import org.elasticsearch.action.admin.indices.close.CloseIndexResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.delete.DeleteMappingResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.admin.indices.open.OpenIndexResponse;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * ElasticSearch管理中心
 * @author leifu
 * @Date 2015-3-21
 * @Time 下午3:37:15
 */
public interface ElasticSearchCenter {
    
    /**
     * 检查索引是否存在
     * @param name 索引名
     * @return
     */
    public boolean checkIndexExists(String name);
    
    /**
     * 创建索引
     * @param name 索引名
     */
    public Result<CreateIndexResponse> createIndex(String name);

    /**
     * 删除索引
     * @param name 索引名
     */
    public Result<DeleteIndexResponse> deleteIndex(String name);

    /**
     * 关闭索引
     * @param name
     */
    public Result<CloseIndexResponse> closeIndex(String name);

    /**
     * 打开索引
     * @param name
     */
    public Result<OpenIndexResponse> openIndex(String name);

    /**
     * 添加mapping
     * @param index 索引名
     * @param typeName 类型名
     * @param source
     */
    public Result<PutMappingResponse> putMapping(String index, String typeName, XContentBuilder source);

    /**
     * 删除mapping
     * @param index 索引名
     * @param typeName 类型名
     */
    public Result<DeleteMappingResponse> deleteMapping(String index, String typeName);

    /**
     * 添加/更新文档
     *
     * @param index
     * @param type
     * @param id
     * @param dataMap
     * @return
     */
    public Result<IndexResponse> mergeDocument(String index, String type, String id, Map<String, Object> dataMap);

    /**
     * 删除文档
     *
     * @param index
     * @param type
     * @param id
     * @return
     */
    public Result<DeleteResponse> deleteDocument(String index, String type, String id);


    /**
     * 批量添加文档,每次批量控制在10Mb左右
     *
     * @param index
     * @param type
     * @param idMap
     * @return
     */
    public Result<BulkResponse> bulkDocuments(String index, String type, LinkedHashMap<String, Map<String, Object>> idMap);

    
    /**
     * 批量删除文档
     * @param index
     * @param type
     * @param ids
     * @return
     */
    public Result<BulkResponse> bulkDeleteDocuments(String index, String type, List<String> ids);
    
    /**
     * 批量更新文档
     * @param index
     * @param type
     * @param idUpdateScriptMap
     * @return
     */
    public Result<BulkResponse> bulkUpdateDocuments(String index, String type, Map<String, String> idUpdateMapScript);
    
    /**
     * scroll特定类型索引
     * 第一次scrollId传递null
     * @param scrollParam
     * @return
     */
    public ScrollResult scrollIndex(ScrollParam scrollParam);

    /**
     * 根据index&type&id获取文档_source
     *
     * @param index
     * @param type
     * @param id
     * @return
     */
    public Result<GetResponse> getSource(String index, String type, String id);
    
    

    /**
     * 获取TransportClient
     *
     * @return
     */
    public Client getClient();

}
