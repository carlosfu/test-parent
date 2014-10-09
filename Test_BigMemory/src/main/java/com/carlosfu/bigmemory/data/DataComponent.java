package com.carlosfu.bigmemory.data;


import java.io.Serializable;

import com.carlosfu.bigmemory.model.DataResult;

/**
 * 数据访问组件
 * @author leifu(original: mobil)
 * @Time 2014-10-9
 * @param <V>
 */
public interface DataComponent<V extends Serializable> {

    /**
     * 添加缓存，如果成功返回对应数据,不存在或超时等返回对应错误状态 @see DataStatusEnum
     *
     * @param key
     * @param value
     * @return
     */
    DataResult<Boolean> add(String key, int exp, final V value);

    /**
     * 添加或替换缓存，如果成功返回对应数据,不存在或超时等返回对应错误状态 @see DataStatusEnum
     *
     * @param key
     * @param value
     * @return
     */
    DataResult<Boolean> set(String key, int exp, final V value);

    /**
     * 删除缓存，如果成功返回对应数据,不存在或超时等返回对应错误状态 @see DataStatusEnum
     *
     * @param key
     * @return
     */
    DataResult<Boolean> remove(String key);

    /**
     * 获取缓存结果，如果成功返回数据,不存在或超时等返回对应错误状态 @see DataStatusEnum
     *
     * @param key
     * @return
     */
    DataResult<V> get(String key);

}
