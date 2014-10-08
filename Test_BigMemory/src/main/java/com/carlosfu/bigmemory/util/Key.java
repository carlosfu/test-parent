package com.carlosfu.bigmemory.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * key封装类 <br/>
 * ketType_${param1}_${param2}... <br/>
 * User: yijunzhang
 * Date: 13-11-17
 * Time: 下午9:46
 */
public class Key {

    /**
     * key md5值
     */
    private String key;

    /**
     * 原始key值
     */
    private String originalKey;

    /**
     * key类型
     */
    private String type;

    /**
     * key类型对象
     */
    private KeyType keyType;

    /**
     * 动态参数
     */
    private Map<String, Object> paramMap;

    /**
     * 用户自定义对象超时时间,优先级最高,单位:秒,0为永久
     */
    private int customExp = -1;

    /**
     * key的value对应的二进制
     */
    private byte[] bytes;

    private Key() {
    }

    /**
     * 直接内容创建key对象
     * @param type      key的类型
     * @param keyContent  key的直接内容
     * @return
     */
    public static Key createWithContent(String type, String keyContent) {
        if (type == null) {
            throw new RuntimeException("keyType is null");
        }
        Key key = new Key();
        key.type = type;
        key.originalKey = keyContent;
        key.key = keyContent;

        return key;
    }

    /**
     * @param type      key的类型
     * @param paramMap  动态参数
     * @param keyParams 生成key的参数 ketType_${keyParams[0]}_${keyParams[1]} ...
     * @return
     */
    public static Key createWithParams(String type, Map<String, Object> paramMap, Object... keyParams) {
        if (type == null) {
            throw new RuntimeException("keyType is null");
        }
        Key key = new Key();
        key.type = type;
        if (paramMap != null && paramMap.size() > 0) {
            key.paramMap = new HashMap<String, Object>();
            key.paramMap.putAll(paramMap);
        }

        StringBuilder buffer = new StringBuilder(type);
        if (keyParams != null && keyParams.length > 0) {
            for (Object param : keyParams) {
                if (param == null || StringUtils.isBlank(String.valueOf(param))) {
                    //参数为空，补0
                    buffer.append("_" + "0");
                } else {
                    buffer.append("_" + param);
                }
            }
        }
        key.originalKey = buffer.toString();
        key.key = MD5(key.originalKey);

        return key;
    }

    private static String MD5(String s) {
        return DigestUtils.md5DigestAsHex(s.getBytes());
    }

    /**
     * @param keyType
     * @param keyParams
     * @return
     * @see #createWithParams(String, java.util.Map, Object...)
     */
    public static Key create(String keyType, Object... keyParams) {
        return createWithParams(keyType, null, keyParams);
    }

    public String key() {
        return key;
    }

    public String keyType() {
        return type;
    }

    public Map<String, Object> paramMap() {
        return paramMap;
    }

    public KeyType getKeyType() {
        return keyType;
    }

    public void setKeyType(KeyType keyType) {
        this.keyType = keyType;
    }

    public int getCustomExp() {
        return customExp;
    }

    public void setCustomExp(int customExp) {
        this.customExp = customExp;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return "Key{" +
                "key='" + key + '\'' +
                ", originalKey='" + originalKey + '\'' +
                ", type='" + type + '\'' +
                ", keyType=" + keyType +
                ", paramMap=" + paramMap +
                ", customExp=" + customExp +
                '}';
    }


    @Override
    public int hashCode() {
        final int PRIME = 31;
        return new HashCodeBuilder(originalKey.hashCode() % 2 == 0 ? originalKey.hashCode() + 1 : originalKey.hashCode(), PRIME)
                .append(type)
                .append(key)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Key key = (Key) obj;
        return new EqualsBuilder()
                .append(key.originalKey, originalKey)
                .append(key.type, this.type)
                .append(key.key, this.key).isEquals();
    }


}
