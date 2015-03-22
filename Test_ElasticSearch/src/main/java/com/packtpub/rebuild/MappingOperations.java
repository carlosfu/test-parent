package com.packtpub.rebuild;

import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sohu.tv.index.data.engine.es.ElasticSearchCenter;
import com.sohu.tv.index.data.engine.es.impl.ElasticSearchCenterImpl;
import com.sohu.tv.index.data.engine.value.Result;

import java.io.IOException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Mapping操作
 * 
 * @author leifu
 * @Date 2015-3-21
 * @Time 下午3:34:42
 */
public class MappingOperations extends ElasticSearchBase {

	@Before
	public void before() {
		// 创建索引
		if (elasticSearchCenter.checkIndexExists(INDEX))
			elasticSearchCenter.deleteIndex(INDEX);
		elasticSearchCenter.createIndex(INDEX);
	}

	@After
	public void after() {
		// 删除mapping和索引
//		elasticSearchCenter.deleteMapping(INDEX, TYPE);
//		elasticSearchCenter.deleteIndex(INDEX);
	}

	/**
	 * 创建mapping
	 */
	@Test
	public void buildMapping() {
		try {
			XContentBuilder builder = jsonBuilder().startObject()
					.field("type1").startObject().field("properties")
					.startObject().field("nested1").startObject().field("type")
					.value("nested").endObject().endObject().endObject()
					.endObject();
			System.out.println("mapping json:\n" + builder.string());
			Result<PutMappingResponse> response = elasticSearchCenter
					.putMapping(INDEX, TYPE, builder);
			if (!response.getValue().isAcknowledged()) {
				System.out.println("Something strange happens");
			}
		} catch (IOException e) {
			System.out.println("Unable to create mapping");
		}
	}
}
