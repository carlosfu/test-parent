package com.carlosfu.hystrix.fallback;

import static org.junit.Assert.*;

import org.junit.Test;

import com.carlosfu.hystrix.offical.example.basic.UserAccount;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class CommandWithStubbedFallback extends HystrixCommand<UserAccount> {

    private final int customerId;
    private final String countryCodeFromGeoLookup;

    protected CommandWithStubbedFallback(int customerId, String countryCodeFromGeoLookup) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.customerId = customerId;
        this.countryCodeFromGeoLookup = countryCodeFromGeoLookup;
    }

    @Override
    protected UserAccount run() {
        throw new RuntimeException("forcing failure for example");
    }

    @Override
    protected UserAccount getFallback() {
        return new UserAccount(customerId, "Unknown Name",
                countryCodeFromGeoLookup, true, true, false);
    }

    public static class UnitTest {

        @Test
        public void test() {
            CommandWithStubbedFallback command = new CommandWithStubbedFallback(1234, "ca");
            UserAccount account = command.execute();
            //查看命令是否错误、fallback
            assertTrue(command.isFailedExecution());
            assertTrue(command.isResponseFromFallback());
            assertEquals(1234, account.customerId);
            assertEquals("ca", account.countryCode);
            assertEquals(true, account.isFeatureXPermitted);
            assertEquals(true, account.isFeatureYPermitted);
            assertEquals(false, account.isFeatureZPermitted);
        }
    }
}
