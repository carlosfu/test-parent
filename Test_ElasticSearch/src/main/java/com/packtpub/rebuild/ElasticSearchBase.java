package com.packtpub.rebuild;

import com.sohu.tv.index.data.engine.es.ElasticSearchCenter;
import com.sohu.tv.index.data.engine.es.impl.ElasticSearchCenterImpl;

/**
 * ElasticSearch 
 * @author leifu
 * @Date 2015-3-21
 * @Time 下午3:45:34
 */
public class ElasticSearchBase {

	protected ElasticSearchCenter elasticSearchCenter = new ElasticSearchCenterImpl();

	/**
	 * index和type
	 */
	protected final static String INDEX = "carlosfu-index";
	protected final static String TYPE = "carlosfu-type";

	/**
	 * mapping的字段
	 */
	protected final static String BIG_IMG = "bimg";
	protected final static String TITLE = "title";
	protected final static String TAGS = "tags";
	protected final static String ID = "id";
	protected final static String INDEX_TIME = "indextime";
	
}
