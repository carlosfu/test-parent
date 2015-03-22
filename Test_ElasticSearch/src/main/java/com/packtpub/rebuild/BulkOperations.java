package com.packtpub.rebuild;

import org.elasticsearch.action.bulk.BulkResponse;
import org.junit.Test;

import com.sohu.tv.index.data.engine.value.Result;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 来自elasticsearch-cookbook
 * 
 * @author leifu
 * @Date 2015年2月28日
 * @Time 下午3:07:08
 */
public class BulkOperations extends ElasticSearchBase {

	@Test
	public void testSingleMerge() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int i = 1001;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(ID, i);
		map.put(BIG_IMG, "img " + i);
		map.put(TAGS, "tag1" + i + ";" + "tag2" + i);
		map.put(TITLE, "title" + i);
		map.put(INDEX_TIME, sdf.format(new Date()));

		elasticSearchCenter.mergeDocument(INDEX, TYPE, String.valueOf(i), map);
	}

	@Test
	public void testBulkMerge() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LinkedHashMap<String, Map<String, Object>> mapList = new LinkedHashMap<String, Map<String, Object>>();
		for (int i = 1; i <= 50; i++) {
			String id = String.valueOf(i);
			// 组装map开始
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(ID, i);
			map.put(BIG_IMG, "img " + i);
			map.put(TAGS, "tag1" + i + ";" + "tag2" + i);
			map.put(TITLE, "title" + i);
			map.put(INDEX_TIME, sdf.format(new Date()));
			// 组装map结束
			mapList.put(id, map);
		}
		// 批量导入
		Result<BulkResponse> response = elasticSearchCenter.bulkDocuments(INDEX, TYPE, mapList);
		System.out.println("response: " + response);
	}

	@Test
	public void testBulkDelete() {
		List<String> ids = new ArrayList<String>();
		for (int i = 1; i <= 50; i++) {
			String id = String.valueOf(i);
			ids.add(id);
		}
		// 批量删除
		Result<BulkResponse> response = elasticSearchCenter.bulkDeleteDocuments(INDEX, TYPE, ids);
		System.out.println("response: " + response);
	}
	
	@Test
	public void testBulkUpdate() {
		Map<String, String> idUpdateMapScript = new HashMap<String, String>();
		for (Integer i = 1; i <= 50; i++) {
			String id = String.valueOf(i);
			String script = "ctx._source.title = 'NBA" + i + "'";
			idUpdateMapScript.put(id, script);
		}
		elasticSearchCenter.bulkUpdateDocuments(INDEX, TYPE, idUpdateMapScript);
	}

}
