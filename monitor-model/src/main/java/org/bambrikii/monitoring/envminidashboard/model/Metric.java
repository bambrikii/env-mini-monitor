package org.bambrikii.monitoring.envminidashboard.model;

import org.bambrikii.monitoring.envminidashboard.model.api.MetricsNamespaceable;

import java.util.Objects;

public class Metric {
    private String code;
    private MetricsNamespaceable namespace;

    public Metric(String code, MetricsNamespaceable namespace) {
        this.code = code;
        this.namespace = namespace;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MetricsNamespaceable getNamespace() {
        return namespace;
    }

    public void setNamespace(MetricsNamespaceable namespace) {
        this.namespace = namespace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metric metric = (Metric) o;
        return Objects.equals(code, metric.code) && Objects.equals(namespace, metric.namespace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, namespace);
    }

    @Override
    public String toString() {
        return "Metric{" + "code='" + code + '\'' + ", namespace=" + namespace + '}';
    }
}
