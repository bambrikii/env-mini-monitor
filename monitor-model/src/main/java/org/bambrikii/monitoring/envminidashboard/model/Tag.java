package org.bambrikii.monitoring.envminidashboard.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Tag implements Taggable {
    private String name;
    private List<MetricsNamespaceable> metricsNamespaces = new ArrayList<>();
    private List<ConnConfig> connConfigs = new ArrayList<>();

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<MetricsNamespaceable> getMetricsNamespaces() {
        return metricsNamespaces;
    }

    public void setMetricsNamespaces(List<MetricsNamespaceable> metricsNamespaces) {
        this.metricsNamespaces = metricsNamespaces;
    }

    @Override
    public List<ConnConfig> getConnConfigs() {
        return connConfigs;
    }

    public void setConnConfigs(List<ConnConfig> connConfigs) {
        this.connConfigs = connConfigs;
    }
}
