package com.carlosfu.exception;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WrongCalculator {
    private Logger LOGGER = LoggerFactory.getLogger(WrongCalculator.class);
    

    public int div(int a, int b) {
        int result = 0;
        try {
            return a / b;
        } catch (Exception e) {
            LOGGER.error("div error: ", e);
        }
        return result;
    }
    
    public int div2(int a, int b) {
        int result = 0;
        try {
            return a / b;
        } catch (Throwable t) {
            LOGGER.error("div2 error: ", t);
        }
        return result;
    }
    
    public int div3(int a, int b) throws Throwable {
        try {
            return a / b;
        } catch (Exception e) {
            throw new Throwable(e);
        }
    }
    
    public int div4(int a, int b) {
        try {
            return a / b;
        } catch (Exception e) {
            LOGGER.error("div error: ", e);
            return 333;
        } finally{
            System.out.println("==============");
        }
    }
    
    public int div5(int a, int b) {
        try {
            return a / b;
        } finally{
            System.out.println("==============");
        }
    }
    
    
    public static class CalTest{
        private Logger logger = LoggerFactory.getLogger(CalTest.class); //无论是异常还是非异常，都执行finally
        
        WrongCalculator cal = new WrongCalculator();
        
        
        @Test
        public void testCal1(){
            logger.info("testCal1 9/0 is "+ cal.div(9, 0));
        }
        
        @Test
        public void testCal2(){
            logger.info("testCal2 9/0 is "+ cal.div2(9, 0));
        }
        
        @Test
        public void testCal3(){
            try {
                logger.info("testCal3 9/0 is "+ cal.div3(9, 0));
            } catch (Throwable e) {
                logger.error("testCal3 error: ", e);
            }
        }
        
        @Test
        public void testCal4(){
            //无论是异常还是非异常，都执行finally
            logger.info("testCal4 9/0 is "+ cal.div4(9, 0));
            logger.info("testCal4 9/3 is "+ cal.div4(9, 3));
        }
        
        @Test
        public void testCal5(){
            //无论是异常还是非异常，都执行finally
            logger.info("testCal4 9/3 is "+ cal.div5(9, 3));
            logger.info("testCal4 9/0 is "+ cal.div5(9, 0));
        }
    }
}


