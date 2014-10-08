package com.carlosfu.bigmemory.util;

import net.sf.json.JSONObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * key类型 封装类
 * User: yijunzhang
 * Date: 13-11-18
 * Time: 上午10:25
 */
public class KeyType implements Serializable, Cloneable {
    private static final long serialVersionUID = 2487406452443167386L;

    //默认一小时
    public static final int DEFAULT_EXP = 60 * 60;

    private static final Logger LOGGER = LoggerFactory.getLogger(KeyType.class);

    public static final String DEFAULT_KEY_TYPE = "default_key_type";

    /**
     * key的类型
     */
    private String type;

    /**
     * 超时时间
     */
    private int timeout;

    /**
     * hbase timeout
     */
    private int hbasetimeout;

    /**
     * api timeout
     */
    private int apitimeout;

    /**
     * 本地缓存失效时间 单位秒
     * eg: 360 , 300-400.
     */
    private String localExpRange;

    /**
     * remote缓存失效时间 单位秒
     * eg: 360 , 300-400.
     */
    private String remoteExpRange;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getLocalExp() {
        return getExp(localExpRange);
    }

    public int getRemoteExp() {
        return getExp(remoteExpRange);
    }

    public String getLocalExpRange() {
        return localExpRange;
    }

    public void setLocalExpRange(String localExpRange) {
        this.localExpRange = localExpRange;
    }

    public String getRemoteExpRange() {
        return remoteExpRange;
    }

    public void setRemoteExpRange(String remoteExpRange) {
        this.remoteExpRange = remoteExpRange;
    }

    private int getExp(String expRange) {
        try {
            String[] exps = expRange.split("\\-");
            if (exps == null || exps.length > 2) {
                return DEFAULT_EXP;
            } else if (exps.length == 2) {
                int exp1 = Integer.parseInt(exps[0]);
                int exp2 = Integer.parseInt(exps[1]);
                if (exp1 < 0 || exp2 < 0) {
                    return DEFAULT_EXP;
                }
                if (exp2 > exp1) {
                    return RandomUtil.getRandomAToB(exp1, exp2);
                } else if (exp1 == exp2) {
                    return exp1;
                }
            } else if (exps.length == 1) {
                int exp = Integer.parseInt(exps[0]);
                if (exp >= 0) {
                    return exp;
                }
            }
            return DEFAULT_EXP;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return DEFAULT_EXP;
        }
    }


    public static KeyType jsonToKeyType(String json) {
        if (json == null || "".equals(json.trim())) {
            return null;
        }
        KeyType keyType = null;
        try {
            JSONObject jsonObject = JSONObject.fromObject(json);
            keyType = new KeyType();

            String type = jsonObject.getString("type");
            if (type == null || "".equals(type.trim())) {
                return null;
            }
            if (jsonObject.has("timeout")) {
                int timeout = jsonObject.getInt("timeout");
                if (timeout <= 10) {
                    return null;
                }
                keyType.setTimeout(timeout);
            }

            if (jsonObject.has("hbasetimeout")) {
                int hbasetimeout = jsonObject.getInt("hbasetimeout");
                if (hbasetimeout <= 10) {
                    return null;
                }
                keyType.setHbasetimeout(hbasetimeout);
            }
            if (jsonObject.has("apitimeout")) {
                int apitimeout = jsonObject.getInt("apitimeout");
                if (apitimeout <= 10) {
                    return null;
                }
                keyType.setApitimeout(apitimeout);
            }

            keyType.setType(type);

            if (jsonObject.has("localExpRange")) {
                String localExpRange = jsonObject.getString("localExpRange");
                if (!validateExpRange(localExpRange)) {
                    return null;
                }
                keyType.setLocalExpRange(localExpRange);
            } else {
                return null;
            }
            if (jsonObject.has("remoteExpRange")) {
                String remoteExpRange = jsonObject.getString("remoteExpRange");
                if (!validateExpRange(remoteExpRange)) {
                    return null;
                }
                keyType.setRemoteExpRange(remoteExpRange);
            } else {
                return null;
            }

            return keyType;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return keyType;
    }

    private static boolean validateExpRange(String expRange) {
        if (expRange == null || "".equals(expRange.trim())) {
            return false;
        }
        String[] exps = expRange.split("\\-");
        if (exps == null || exps.length > 2) {
            return false;
        } else if (exps.length == 2) {
            int exp1 = Integer.parseInt(exps[0]);
            int exp2 = Integer.parseInt(exps[1]);
            if (exp1 < 0 || exp2 < 0 || exp1 > exp2) {
                return false;
            }
        } else if (exps.length == 1) {
            int exp = Integer.parseInt(exps[0]);
            if (exp < 0) {
                return false;
            }
        }
        return true;

    }

    public static boolean validateKeyType(KeyType keyType) {
        try {
            if (keyType == null || "".equals(keyType.getType().trim())) {
                return false;
            }
            if (!validateExpRange(keyType.getLocalExpRange())) {
                return false;
            }
            if (!validateExpRange(keyType.getRemoteExpRange())) {
                return false;
            }
            int timeout = keyType.getTimeout();
            if (timeout <= 10) {
                return false;
            }
            int hbasetimeout = keyType.getHbasetimeout();
            if (hbasetimeout <= 10) {
                return false;
            }
            int apitimeout = keyType.getApitimeout();
            if (apitimeout <= 10) {
                return false;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    public static String keyTypeToJson(KeyType keyType) {
        if (keyType == null) {
            return null;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", keyType.type);
        jsonObject.put("localExpRange", keyType.localExpRange);
        jsonObject.put("remoteExpRange", keyType.remoteExpRange);
        jsonObject.put("timeout", keyType.timeout);
        jsonObject.put("hbasetimeout", keyType.hbasetimeout);
        jsonObject.put("apitimeout", keyType.apitimeout);
        return jsonObject.toString();
    }

    @Override
    public String toString() {
        return "KeyType{" +
                "type='" + type + '\'' +
                ", timeout=" + timeout +
                ", hbasetimeout=" + hbasetimeout +
                ", apitimeout=" + apitimeout +
                ", localExpRange='" + localExpRange + '\'' +
                ", remoteExpRange='" + remoteExpRange + '\'' +
                '}';
    }

    public int getHbasetimeout() {
        return hbasetimeout;
    }

    public void setHbasetimeout(int hbasetimeout) {
        this.hbasetimeout = hbasetimeout;
    }

    public int getApitimeout() {
        return apitimeout;
    }

    public void setApitimeout(int apitimeout) {
        this.apitimeout = apitimeout;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        return new HashCodeBuilder(timeout % 2 == 0 ? timeout + 1 : timeout, PRIME)
                .append(type)
                .append(localExpRange)
                .append(remoteExpRange)
                .append(timeout)
                .append(apitimeout)
                .append(hbasetimeout)
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
        KeyType keyType = (KeyType) obj;
        return new EqualsBuilder()
                .append(keyType.getType(), this.getType())
                .append(keyType.getApitimeout(), this.getApitimeout())
                .append(keyType.getLocalExpRange(), this.getLocalExpRange())
                .append(keyType.getRemoteExpRange(), this.getRemoteExpRange())
                .append(keyType.getHbasetimeout(), this.getHbasetimeout())
                .append(keyType.getTimeout(), this.getTimeout()).isEquals();
    }

    /**
     * 判断除type之外的字段是否相等
     * @param obj
     * @return
     */
    public boolean equalsValue(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        KeyType keyType = (KeyType) obj;
        return new EqualsBuilder()
                .append(keyType.getApitimeout(), this.getApitimeout())
                .append(keyType.getLocalExpRange(), this.getLocalExpRange())
                .append(keyType.getRemoteExpRange(), this.getRemoteExpRange())
                .append(keyType.getHbasetimeout(), this.getHbasetimeout())
                .append(keyType.getTimeout(), this.getTimeout()).isEquals();
    }

    @Override
    public KeyType clone() {
        try {
            return (KeyType) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }
    }

}
