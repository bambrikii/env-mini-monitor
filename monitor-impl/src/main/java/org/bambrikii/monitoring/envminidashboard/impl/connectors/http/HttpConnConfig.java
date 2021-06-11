package org.bambrikii.monitoring.envminidashboard.impl.connectors.http;

import org.bambrikii.monitoring.envminidashboard.model.HostConnConfig;

import java.util.Objects;

public class HttpConnConfig extends HostConnConfig {
    private String urlPath;
    private String method;

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HttpConnConfig that = (HttpConnConfig) o;
        return Objects.equals(urlPath, that.urlPath) && Objects.equals(method, that.method);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), urlPath, method);
    }
}
