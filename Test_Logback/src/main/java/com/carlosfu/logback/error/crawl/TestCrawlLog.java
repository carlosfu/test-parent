package com.carlosfu.logback.error.crawl;

import java.util.concurrent.TimeUnit;

import com.carlosfu.logback.enums.LogLevel;

public class TestCrawlLog {
    private static ErrorObject errorObject = new ErrorObject();
    
    public static void main(String[] args) throws InterruptedException {
        //制造异常
        int realErrorCount = 0;
        while(true){
            errorObject.errorMethod(LogLevel.ERROR);
            realErrorCount++;
            TimeUnit.SECONDS.sleep(1);
        }
        
        //收集异常
        
        
    }
    
    
}
