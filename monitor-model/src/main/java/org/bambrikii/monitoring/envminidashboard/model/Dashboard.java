package org.bambrikii.monitoring.envminidashboard.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bambrikii.monitoring.envminidashboard.connectors.Connectable;
import org.bambrikii.monitoring.envminidashboard.loaders.MetricsFamilyLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Dashboard {
    private List<Environment> environments = new ArrayList<>();
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private List<MetricsFamilyLoader<ConnectionSetting, Connectable<ConnectionSetting>>> metricsFamilyLoaders = new ArrayList<>();

    @Setter(AccessLevel.NONE)
    private Map<MetricsFamily, List<MetricsFamilyLoader>> metricsFamilyLoadersByMetricsFamily = new HashMap<>();

    public Dashboard addMetricsFamilyLoaders(List<MetricsFamilyLoader> metricsFamilyLoaders) {
        metricsFamilyLoaders.forEach(this::addMetricsFamilyLoader);
        return this;
    }

    public Dashboard addMetricsFamilyLoader(MetricsFamilyLoader metricsFamilyLoader) {
        List<MetricsFamily> metricsFamilies = metricsFamilyLoader.getMetricsFamilies();
        for (MetricsFamily metricsFamily : metricsFamilies) {
            metricsFamilyLoadersByMetricsFamily.computeIfAbsent(metricsFamily, (metricsFamily1) -> new ArrayList<>());
            metricsFamilyLoadersByMetricsFamily.get(metricsFamily).add(metricsFamilyLoader);
        }
        return this;
    }
}
