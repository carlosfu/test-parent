package com.carlosfu.spi;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 协同过滤推荐实现
 * 
 * @author leifu
 * @Time 2014年10月11日
 */
public class CfHomeRecommend implements HomeRecommend {
    private static final Logger logger = LoggerFactory.getLogger(CfHomeRecommend.class);

    @Override
    public List<String> recommend(String keyword) {
        List<String> list = Arrays.asList("cf-1", "cf-2", "cf-3");
        logger.info("cf rec: " + list);
        return list;
    }

}
