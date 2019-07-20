package org.bambrikii.monitoring.envminidashboard.model;

import lombok.Getter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
public class TagMappingContainer {
    private Set<MetricsFamily> metricsFamilies = new LinkedHashSet<>();
    private Set<ConnectionSetting> connectionSettings = new LinkedHashSet<>();

    public TagMappingContainer ensureMetricsFamily(MetricsFamily metricsFamily) {
        if (!metricsFamilies.contains(metricsFamily)) {
            metricsFamilies.add(metricsFamily);
        }
        return this;
    }

    public TagMappingContainer ensureConnectionSettings(ConnectionSetting connectionSetting) {
        if (!this.connectionSettings.contains(connectionSetting)) {
            this.connectionSettings.add(connectionSetting);
        }
        return this;
    }
}
