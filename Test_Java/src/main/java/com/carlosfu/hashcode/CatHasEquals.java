package com.carlosfu.hashcode;

/**
 * 重写equals
 * @author leifu
 * @Time 2014年10月5日
 */
public class CatHasEquals extends Cat{
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CatHasEquals other = (CatHasEquals) obj;
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
