package com.carlosfu.hystrix.helloworld;

public class UserAccount {
	public final int customerId;
	public final String name;
	public final String countryCode;
	public final boolean isFeatureXPermitted;
	public final boolean isFeatureYPermitted;
	public final boolean isFeatureZPermitted;

	public UserAccount(int customerId, String name, String countryCode,
			boolean isFeatureXPermitted, boolean isFeatureYPermitted,
			boolean isFeatureZPermitted) {
		this.customerId = customerId;
		this.name = name;
		this.countryCode = countryCode;
		this.isFeatureXPermitted = isFeatureXPermitted;
		this.isFeatureYPermitted = isFeatureYPermitted;
		this.isFeatureZPermitted = isFeatureZPermitted;
	}

	public int getCustomerId() {
		return customerId;
	}

	public String getName() {
		return name;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public boolean isFeatureXPermitted() {
		return isFeatureXPermitted;
	}

	public boolean isFeatureYPermitted() {
		return isFeatureYPermitted;
	}

	public boolean isFeatureZPermitted() {
		return isFeatureZPermitted;
	}
	
	
}