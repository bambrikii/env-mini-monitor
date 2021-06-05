package org.bambrikii.monitoring.envminidashboard.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Tag implements Taggable {
    private String name;
    private List<MetricsNamespaceable> metricsFamilies = new ArrayList<>();
    private List<ConnConfig> connectionSettings = new ArrayList<>();

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<MetricsNamespaceable> getMetricsNamespaces() {
        return metricsFamilies;
    }

    public void setMetricsFamilies(List<MetricsNamespaceable> metricsFamilies) {
        this.metricsFamilies = metricsFamilies;
    }

    @Override
    public List<ConnConfig> getConnConfigs() {
        return connectionSettings;
    }

    public void setConnectionSettings(List<ConnConfig> connectionSettings) {
        this.connectionSettings = connectionSettings;
    }
}
