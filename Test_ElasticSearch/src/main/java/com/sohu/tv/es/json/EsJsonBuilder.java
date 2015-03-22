package com.sohu.tv.es.json;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.junit.Test;

/**
 * ElasticSearch XContentBuilder简单测试
 * 
 * @author leifu
 * @Date 2015-3-21
 * @Time 下午3:26:46
 */
public class EsJsonBuilder {

	@Test
	public void test1() throws IOException {
		XContentBuilder builder = jsonBuilder().startObject().field("type1")
				.startObject().field("properties").startObject()
				.field("nested1").startObject().field("type").value("nested")
				.endObject().endObject().endObject().endObject();
		System.out.println(builder.prettyPrint().string());
	}

	/**
	 * startObject(): { endObject(): }
	 * 
	 * @throws IOException
	 */
	@Test
	public void test2() throws IOException {
		XContentBuilder builder = jsonBuilder().startObject().endObject();
		System.out.println(builder.prettyPrint().string());
	}

	/**
	 * startArray(): [ endArray(): ]
	 * 
	 * @throws IOException
	 */
	@Test
	public void test3() throws IOException {
		XContentBuilder builder = jsonBuilder().startArray().endArray();
		System.out.println(builder.prettyPrint().string());
	}

	@Test
	public void test4() throws IOException {
		XContentBuilder builder = jsonBuilder().startObject().field("a", 1)
				.field("b").value("haha").field("data").startObject()
				.endObject().endObject();
		System.out.println(builder.prettyPrint().string());
	}

}
