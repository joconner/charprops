package com.joconner.g11n.charprop.model;

import java.util.Objects;

/**
 *
 * @author joconner
 */
public class HealthCheckStatus {
    private int status;
    private String msg;

    public HealthCheckStatus() {
    }

    public HealthCheckStatus(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.status;
        hash = 43 * hash + Objects.hashCode(this.msg);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HealthCheckStatus other = (HealthCheckStatus) obj;
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.msg, other.msg)) {
            return false;
        }
        return true;
    }
        
    
    
}
