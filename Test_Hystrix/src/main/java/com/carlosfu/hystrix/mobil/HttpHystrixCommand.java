package com.carlosfu.hystrix.mobil;

import org.junit.Test;

import com.carlosfu.util.HttpRequestUtil;

/**
 * http Command超时验证
 * @author leifu
 * @Date 2014年12月8日
 * @Time 下午2:47:25
 */
public class HttpHystrixCommand extends DataComponentCommand<String>{

	/**
	 * http url
	 */
	private String url;

	public HttpHystrixCommand(String commandKey, String groupKey,
			String poolKey, int timeout, int poolSize) {
		super(commandKey, groupKey, poolKey, timeout, poolSize);
	}

	@Override
	protected String run() throws Exception {
		return HttpRequestUtil.get(url, "gbk");
	}
	
	

	@Override
	protected String getFallback() {
		return "hehe";
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static class UnitTest {
		@Test
		public void testSuccess() {
			String response = "";
			HttpHystrixCommand command = new HttpHystrixCommand("commandKey", "groupKey", "poolKey", 2000, 5);
			command.setUrl("http://www.baidu.com");
			try {
				response = command.queue().get();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("response: " + response);
		}
		
		/**
		 * 超时进入fallback
		 */
		@Test
		public void testTimeOutToFallBack() {
			String response = "";
			HttpHystrixCommand command = new HttpHystrixCommand("commandKey", "groupKey", "poolKey", 1000, 5);
			command.setUrl("http://www32.baidu.com");
			try {
				response = command.queue().get();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("response: " + response);
		}
		

	}
}
