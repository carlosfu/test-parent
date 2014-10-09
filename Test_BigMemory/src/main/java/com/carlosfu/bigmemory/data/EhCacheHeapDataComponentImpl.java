package com.carlosfu.bigmemory.data;


import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carlosfu.bigmemory.enums.DataLevelEnum;
import com.carlosfu.bigmemory.enums.DataStatusEnum;
import com.carlosfu.bigmemory.model.DataResult;

import java.io.Serializable;

/**
 * 堆内内存
 * @author leifu(original: mobil)
 * @Time 2014-10-9
 * @param <V>
 */
public class EhCacheHeapDataComponentImpl<V extends Serializable> implements DataComponent<V> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Ehcache cache;

    @Override
    public DataResult<Boolean> add(String key, int exp, V value) {
        try {
            //已存在
            if (!containsKey(key)) {
                return new DataResult<Boolean>(DataStatusEnum.ALREADY_EXISTS, DataLevelEnum.LOCALCACHE, Boolean.FALSE);
            }
            if (value == null) {
                return new DataResult<Boolean>(DataStatusEnum.ARGS_ERROR, DataLevelEnum.LOCALCACHE, Boolean.FALSE);
            }
            Element e;
            if (exp == 0) {
                e = new Element(key, value);
            } else {
                e = new Element(key, value, 0, exp);
            }
            cache.put(e);
            return new DataResult<Boolean>(DataStatusEnum.SUCCESS, DataLevelEnum.LOCALCACHE, Boolean.TRUE);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new DataResult<Boolean>(DataStatusEnum.INNER_ERROR, DataLevelEnum.LOCALCACHE, Boolean.FALSE);
        }
    }

    @Override
    public DataResult<Boolean> set(String key, int exp, V value) {
        try {
            if (value == null) {
                return new DataResult<Boolean>(DataStatusEnum.ARGS_ERROR, DataLevelEnum.LOCALCACHE, Boolean.FALSE);
            }
            Element e;
            if (exp == 0) {
                e = new Element(key, value);
            } else {
                e = new Element(key, value, 0, exp);
            }
            cache.put(e);
            return new DataResult<Boolean>(DataStatusEnum.SUCCESS, DataLevelEnum.LOCALCACHE, Boolean.TRUE);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new DataResult<Boolean>(DataStatusEnum.INNER_ERROR, DataLevelEnum.LOCALCACHE, Boolean.FALSE);
        }
    }

    @Override
    public DataResult<Boolean> remove(String key) {
        try {
            cache.remove(key);
            return new DataResult<Boolean>(DataStatusEnum.SUCCESS, DataLevelEnum.LOCALCACHE, Boolean.TRUE);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new DataResult<Boolean>(DataStatusEnum.INNER_ERROR, DataLevelEnum.LOCALCACHE, Boolean.FALSE);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public DataResult<V> get(String key) {
        try {
            Element e = cache.get(key);
            if (e != null && !e.isExpired() && e.getObjectValue() != null) {
                Object obj = e.getObjectValue();
                if (obj != null) {
                    V v = (V) obj;
                    return new DataResult<V>(DataStatusEnum.SUCCESS, DataLevelEnum.LOCALCACHE, v);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new DataResult<V>(DataStatusEnum.INNER_ERROR, DataLevelEnum.LOCALCACHE, null);
        }
        return new DataResult<V>(DataStatusEnum.NOT_EXIST, DataLevelEnum.LOCALCACHE, null);
    }

    public boolean containsKey(String key) {
        Element e = cache.get(key);
        return e == null || e.isExpired();
    }

    public void setCache(Ehcache cache) {
        this.cache = cache;
    }

}
