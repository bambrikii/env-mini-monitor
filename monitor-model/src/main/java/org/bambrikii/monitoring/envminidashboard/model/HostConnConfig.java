package org.bambrikii.monitoring.envminidashboard.model;

import java.util.Objects;

public abstract class HostConnConfig extends ConnConfig {
    private String host;
    private Integer port = 22;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HostConnConfig that = (HostConnConfig) o;
        return host.equals(that.host) && port.equals(that.port);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port);
    }

    @Override
    public String toString() {
        return "HostConnConfig{" + "host='" + host + '\'' + ", port=" + port + '}';
    }
}
