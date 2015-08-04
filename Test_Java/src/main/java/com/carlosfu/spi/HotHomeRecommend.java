package com.carlosfu.spi;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 热门推荐实现
 * 
 * @author leifu
 * @Time 2014年10月11日
 */
public class HotHomeRecommend implements HomeRecommend {
    private static final Logger logger = LoggerFactory.getLogger(HotHomeRecommend.class);

    @Override
    public List<String> recommend(String keyword) {
        List<String> list = Arrays.asList("hot-1", "hot-2", "hot-3");
        logger.info("hot rec: " + list);
        return list;
    }

}
