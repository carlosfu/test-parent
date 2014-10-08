package com.carlosfu.bigmemory.util;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * User: yijunzhang
 * Date: 13-11-18
 * Time: 上午10:30
 */
public class RandomUtil {

     protected static final Logger logger = LoggerFactory.getLogger(RandomUtil.class);

    private static final Random r = ThreadLocalRandom.current();

    /**
     * a 到 b取随机数
     * @param a
     * @param b
     */
    public static int getRandomAToB(int a, int b) {
        return (int)Math.round(Math.random() * (b - a) + a);
    }


    /**
    *
    */
   public static boolean ranBoolean() {
       return r.nextBoolean();
   }

   /**
    * 随机指定范围内N个不重复的数 最简单最基本的方法
    *
    * @param min 指定范围最小值 被包含
    * @param max 指定范围最大值 不被包含
    * @param n 随机数个数
    */
   public static int[] randomCommon(int min, int max, int n) {
       if (n >= (max - min + 1)) {
           n = max;
       }
       if (max < min) {
           return new int[]{};
       }
       max += 1; //保证min被包含 max不包含
       int[] result = new int[n];
       for (int z = 0; z < n; z++) {
           result[z] = -1;
       }
       int count = 0;
       while (count < n) {
           int num = (int) (r.nextDouble() * (max - min)) + min;
           boolean flag = true;
           for (int j = 0; j < n; j++) {
               if (num == (result[j] + 1)) {
                   flag = false;
                   break;
               }
           }
           if (flag) {
               result[count] = num - 1; //保证min被包含 max不包含
               count++;
           }
       }
       return result;
   }

   /**
    * 随机获得算法代码
    *
    */

   public static String getStrategyCode(long randomNum, String str) {
       logger.debug("traffic_distribute:{}", str);
       String[] traffics = str.split(";");
       for (String traffic : traffics) {
           String[] item = traffic.split("_");
           String[] num = item[0].split("-");
           int min = NumberUtils.toInt(num[0]);
           int max = NumberUtils.toInt(num[1]);
           String strategy = item[1];
           if (randomNum >= min && randomNum <= max) {
               return strategy;
           }
       }
       return "0";
    }

   /**
    * memcache中随机时间
    * @param time 例如输入10,返回10-19
    *
    */
   public static int randomTimeout(int time) {
       return time + randomCommon(0, time, 1)[0];
   }
}
