package com.carlosfu.hashcode;

/**
 * 重写hashcode和equals
 * @author leifu
 * @Time 2014年10月5日
 */
public class CatHasHashcodeAndEqual extends Cat{
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + super.getAge();
		result = prime * result + ((super.getName() == null) ? 0 : super.getName().hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CatHasHashcodeAndEqual other = (CatHasHashcodeAndEqual) obj;
		if (super.getAge() != other.getAge())
			return false;
		if (super.getName() == null) {
			if (other.getName() != null)
				return false;
		} else if (!super.getName().equals(other.getName()))
			return false;
		return true;
	}
}
