package com.carlosfu.redis.cluster.list.manage;

import java.io.Serializable;

public class DramaMessage implements Serializable {
    private Integer location;
    private String content;

    public DramaMessage(Integer location, String content) {
        this.location = location;
        this.content = content;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DramaMessage other = (DramaMessage) obj;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DramaMessage [location=" + location + ", content=" + content + "]";
    }

}
