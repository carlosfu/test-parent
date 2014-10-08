package com.carlosfu.bigmemory.data;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carlosfu.bigmemory.util.DataLevelEnum;
import com.carlosfu.bigmemory.util.DataResult;
import com.carlosfu.bigmemory.util.DataStatusEnum;
import com.carlosfu.bigmemory.util.Key;
import com.carlosfu.bigmemory.util.KeyType;
import com.carlosfu.serialize.ProtostuffSerializer;

import java.io.Serializable;

/**
 * ehcache数据组件实现
 * User: yijunzhang
 * Date: 13-11-14
 * Time: 下午2:17
 */
public class EhCacheDataComponentImpl<V extends Serializable> implements DataComponent<V> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Ehcache cache;

    private ProtostuffSerializer protostuffSerializer;

    @Override
    public DataResult<Boolean> add(Key key, V value) {
        try {
            //已存在
            if (!containsKey(key.key())) {
                return new DataResult<Boolean>(DataStatusEnum.ALREADY_EXISTS, DataLevelEnum.LOCALCACHE, Boolean.FALSE);
            }
            if (value == null) {
                return new DataResult<Boolean>(DataStatusEnum.ARGS_ERROR, DataLevelEnum.LOCALCACHE, Boolean.FALSE);
            }
            KeyType keyType = key.getKeyType();
            int exp;
            if (key.getCustomExp() > -1) {
                exp = key.getCustomExp();
            } else {
                exp = keyType.getLocalExp();
            }
            Element e;
            byte[] bytes = protostuffSerializer.serialize(value);
            if (exp == 0) {
                e = new Element(key.key(), bytes);
            } else {
                e = new Element(key.key(), bytes, 0, exp);
            }
            cache.put(e);
            return new DataResult<Boolean>(DataStatusEnum.SUCCESS, DataLevelEnum.LOCALCACHE, Boolean.TRUE);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new DataResult<Boolean>(DataStatusEnum.INNER_ERROR, DataLevelEnum.LOCALCACHE, Boolean.FALSE);
        }
    }

    @Override
    public DataResult<Boolean> set(Key key, V value) {
        try {
            if (value == null) {
                return new DataResult<Boolean>(DataStatusEnum.ARGS_ERROR, DataLevelEnum.LOCALCACHE, Boolean.FALSE);
            }
            KeyType keyType = key.getKeyType();
            int exp;
            if (key.getCustomExp() > -1) {
                exp = key.getCustomExp();
            } else {
                exp = keyType.getLocalExp();
            }

            Element e;
            byte[] bytes = protostuffSerializer.serialize(value);
            if (exp == 0) {
                e = new Element(key.key(), bytes);
            } else {
                e = new Element(key.key(), bytes, 0, exp);
            }
            cache.put(e);
            return new DataResult<Boolean>(DataStatusEnum.SUCCESS, DataLevelEnum.LOCALCACHE, Boolean.TRUE);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new DataResult<Boolean>(DataStatusEnum.INNER_ERROR, DataLevelEnum.LOCALCACHE, Boolean.FALSE);
        }
    }

    @Override
    public DataResult<Boolean> remove(Key key) {
        try {
            cache.remove(key.key());
            return new DataResult<Boolean>(DataStatusEnum.SUCCESS, DataLevelEnum.LOCALCACHE, Boolean.TRUE);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new DataResult<Boolean>(DataStatusEnum.INNER_ERROR, DataLevelEnum.LOCALCACHE, Boolean.FALSE);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public DataResult<V> get(Key key) {
        try {
            Element e = cache.get(key.key());
            if (e != null && !e.isExpired() && e.getObjectValue() != null) {
                byte[] bytes = (byte[]) e.getObjectValue();
                V v = protostuffSerializer.deserialize(bytes);
                if (v != null) {
                    return new DataResult<V>(DataStatusEnum.SUCCESS, DataLevelEnum.LOCALCACHE, v);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new DataResult<V>(DataStatusEnum.INNER_ERROR, DataLevelEnum.LOCALCACHE, null);
        }
        return new DataResult<V>(DataStatusEnum.NOT_EXIST, DataLevelEnum.LOCALCACHE, null);
    }

    public void setProtostuffSerializer(ProtostuffSerializer protostuffSerializer) {
        this.protostuffSerializer = protostuffSerializer;
    }

    public boolean containsKey(String key) {
        Element e = cache.get(key);
        return e == null || e.isExpired();
    }

    public void setCache(Ehcache cache) {
        this.cache = cache;
    }
}
