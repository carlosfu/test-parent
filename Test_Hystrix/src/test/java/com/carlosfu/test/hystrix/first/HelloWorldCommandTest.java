package com.carlosfu.test.hystrix.first;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carlosfu.hystrix.first.HelloWorldCommand;

public class HelloWorldCommandTest {

	private Logger logger = LoggerFactory.getLogger(HelloWorldCommandTest.class);

	@Test
	public void testSynHystrix() {
		// 每个Command对象只能调用一次,不可以重复调用,
		// 重复调用对应异常信息:This instance can only be executed once. Please
		// instantiate a new instance.
		HelloWorldCommand helloWorldCommand = new HelloWorldCommand(
				"Synchronous-hystrix");
		// 使用execute()同步调用代码,效果等同于:helloWorldCommand.queue().get();
		String result = helloWorldCommand.execute();
		logger.info("Syn result=" + result);
	}

	@Test
	public void testAsynHystrix() {
		HelloWorldCommand helloWorldCommand = new HelloWorldCommand(
				"Asynchronous-hystrix");
		// 异步调用,可自由控制获取结果时机,
		Future<String> future = helloWorldCommand.queue();
		// get操作不能超过command定义的超时时间,默认:1秒
		String result = null;
		try {
			result = future.get(100, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		} catch (ExecutionException e) {
			logger.error(e.getMessage(), e);
		} catch (TimeoutException e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("asyn result=" + result);
	}

}
