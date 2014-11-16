package com.carlosfu.hystrix.fallback;

import java.util.concurrent.TimeUnit;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

//重载HystrixCommand 的getFallback方法实现逻辑
public class MyFallBackCommand extends HystrixCommand<String> {
	private final String name;

	public MyFallBackCommand(String name) {
		/* 配置依赖超时时间,500毫秒 */
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloWorldGroup"))
				.andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
				.withExecutionIsolationThreadTimeoutInMilliseconds(500)));
		this.name = name;
	}

	@Override
	protected String getFallback() {
		return "exeucute Falled";
	}

	@Override
	protected String run() throws Exception {
		// sleep 1 秒,调用会超时
		TimeUnit.MILLISECONDS.sleep(1000);
		return "Hello " + name + " thread:" + Thread.currentThread().getName();
	}

	public static void main(String[] args) throws Exception {
		MyFallBackCommand command = new MyFallBackCommand("test-Fallback");
		String result = command.execute();
		System.out.println("fallback: " + result);
	}
}
/*
 * 运行结果:getFallback() 调用运行 getFallback executed
 */