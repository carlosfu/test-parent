package com.carlosfu.spi;

import java.util.List;

/**
 * 推荐接口
 * 
 * @author leifu
 * @Time 2014年10月11日
 */
public interface HomeRecommend {
    
    List<String> recommend(String keyword);
}
