package com.carlosfu.bigmemory.data;


import java.io.Serializable;

import com.carlosfu.bigmemory.util.DataResult;
import com.carlosfu.bigmemory.util.Key;

/**
 * 数据访问组件key-value抽象接口
 * User: yijunzhang
 * Date: 13-11-15
 * Time: 上午9:49
 */
public interface DataComponent<V extends Serializable> {

    /**
     * 添加缓存，如果成功返回对应数据,不存在或超时等返回对应错误状态 @see DataStatusEnum
     *
     * @param key
     * @param value
     * @return
     */
    DataResult<Boolean> add(final Key key, final V value);

    /**
     * 添加或替换缓存，如果成功返回对应数据,不存在或超时等返回对应错误状态 @see DataStatusEnum
     *
     * @param key
     * @param value
     * @return
     */
    DataResult<Boolean> set(final Key key, final V value);

    /**
     * 删除缓存，如果成功返回对应数据,不存在或超时等返回对应错误状态 @see DataStatusEnum
     *
     * @param key
     * @return
     */
    DataResult<Boolean> remove(final Key key);

    /**
     * 获取缓存结果，如果成功返回数据,不存在或超时等返回对应错误状态 @see DataStatusEnum
     *
     * @param key
     * @return
     */
    DataResult<V> get(final Key key);

}
