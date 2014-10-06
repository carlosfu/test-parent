package com.carlosfu.hashcode;

/**
 * 重写hashcode
 * @author leifu
 * @Time 2014年10月5日
 */
public class CatHasHashcode extends Cat{
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + super.getAge();
		result = prime * result + ((super.getName() == null) ? 0 : super.getName().hashCode());
		return result;
	}
	
}
