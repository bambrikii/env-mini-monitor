package org.bambrikii.monitoring.envminidashboard.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class MetricsNamespaceResult {
    private String code;
    private List<MetricsResult> metrics = new ArrayList<>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<MetricsResult> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<MetricsResult> metrics) {
        this.metrics = metrics;
    }
}
