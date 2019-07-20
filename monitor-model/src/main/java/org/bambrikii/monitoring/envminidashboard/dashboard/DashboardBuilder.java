package org.bambrikii.monitoring.envminidashboard.dashboard;

import org.bambrikii.monitoring.envminidashboard.loaders.MetricsFamilyLoader;
import org.bambrikii.monitoring.envminidashboard.model.*;

import java.util.ArrayList;
import java.util.List;

public class DashboardBuilder {
    private Environment environment;
    private List<Environment> environments = new ArrayList<>();
    private List<MetricsFamilyLoader> metricsFamilyLoaders = new ArrayList<>();

    public DashboardBuilder env(String code) {
        environment = new Environment();
        environment.setCode(code);
        environments.add(environment);
        return this;
    }

    public DashboardBuilder metricsFamily(MetricsFamily metricsFamily, Tag... tags) {
        environment.addMetricsFamily(metricsFamily, tags);
        return this;
    }

    public DashboardBuilder metricsFamilyLoader(MetricsFamilyLoader metricsFamilyLoader) {
        metricsFamilyLoaders.add(metricsFamilyLoader);
        return this;
    }

    public DashboardBuilder connectionSettings(ConnectionSetting connectionSetting, Tag... tags) {
        environment.addConnectionMapping(connectionSetting, tags);
        return this;
    }

    public Dashboard buildDashboard() {
        Dashboard dashboard = new Dashboard();
        dashboard.getEnvironments().addAll(environments);
        dashboard.addMetricsFamilyLoaders(metricsFamilyLoaders);
        return dashboard;
    }
}
