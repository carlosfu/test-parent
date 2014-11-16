package com.carlosfu.hystrix.first;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class HelloWorldCommand extends HystrixCommand<String> {
	private final String name;

	public HelloWorldCommand(String name) {
		// 最少配置:指定命令组名(CommandGroup)
		super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
		this.name = name;
	}

	@Override
	protected String run() {
		// 依赖逻辑封装在run()方法中
		return "Hello " + name + " thread:" + Thread.currentThread().getName();
	}

	
}
// 运行结果: run()方法在不同的线程下执行
// result=Hello Synchronous-hystrix thread:hystrix-HelloWorldGroup-1
// result=Hello Asynchronous-hystrix thread:hystrix-HelloWorldGroup-2
// mainThread=main