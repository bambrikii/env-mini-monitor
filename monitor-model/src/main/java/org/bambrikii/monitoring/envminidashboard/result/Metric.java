package org.bambrikii.monitoring.envminidashboard.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bambrikii.monitoring.envminidashboard.model.MetricsNamespaceable;

import java.util.Objects;

@Getter
@Setter
@ToString
public class Metric {
    private String code;
    private MetricsNamespaceable ns;

    public Metric(String code, MetricsNamespaceable ns) {
        this.code = code;
        this.ns = ns;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MetricsNamespaceable getNs() {
        return ns;
    }

    public void setNs(MetricsNamespaceable ns) {
        this.ns = ns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metric metric = (Metric) o;
        return Objects.equals(code, metric.code) && Objects.equals(ns, metric.ns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, ns);
    }
}
