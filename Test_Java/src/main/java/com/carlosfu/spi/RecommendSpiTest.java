package com.carlosfu.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * spi测试类(注意META-INF/services/下的文件名必须是接口的全路径: 内容是具体实现类的全路径) 可以简单理解为初级的IOC
 * 
 * @author leifu
 * @Time 2014年10月11日
 */
public class RecommendSpiTest {
    private static final Logger logger = LoggerFactory.getLogger(RecommendSpiTest.class);

    public static void main(String[] args) {
        /**
         * jdk提供服务实现查找的一个工具类：java.util.ServiceLoader
         */
        ServiceLoader<HomeRecommend> s = ServiceLoader.load(HomeRecommend.class);
        Iterator<HomeRecommend> searchs = s.iterator();
        if (searchs.hasNext()) {
            HomeRecommend curSearch = searchs.next();
            curSearch.recommend("test");
        } else {
            logger.error("don't find serive");
        }
    }
}
