package org.bambrikii.monitoring.envminidashboard.model;

import lombok.AccessLevel;
import lombok.Setter;

import java.util.Objects;

public class MetricsNamespace implements MetricsNamespaceable {
    @Setter(AccessLevel.NONE)
    private String code;

    public MetricsNamespace(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetricsNamespace that = (MetricsNamespace) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "MetricsNamespace{" +
                "code='" + code + '\'' +
                '}';
    }
}
