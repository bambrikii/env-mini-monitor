package org.bambrikii.monitoring.envminidashboard.dashboard;

import org.bambrikii.monitoring.envminidashboard.loaders.MetricsFamilyLoader;
import org.bambrikii.monitoring.envminidashboard.model.ConnectionSettingable;
import org.bambrikii.monitoring.envminidashboard.model.Dashboard;
import org.bambrikii.monitoring.envminidashboard.model.Dashboardable;
import org.bambrikii.monitoring.envminidashboard.model.Environment;
import org.bambrikii.monitoring.envminidashboard.model.Environmentable;
import org.bambrikii.monitoring.envminidashboard.model.MetricsFamilible;
import org.bambrikii.monitoring.envminidashboard.model.Tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DashboardBuilder {
    private Environment environment;
    private List<Environmentable> environments = new ArrayList<>();
    private List<MetricsFamilyLoader> metricsFamilyLoaders = new ArrayList<>();

    public DashboardBuilder env(String code) {
        environment = new Environment();
        environment.setCode(code);
        environments.add(environment);
        return this;
    }

    public DashboardBuilder metricsFamily(MetricsFamilible metricsFamily, Tag... tags) {
        environment.addMetricsFamily(metricsFamily, tags);
        return this;
    }

    public DashboardBuilder metricsFamilyLoader(MetricsFamilyLoader metricsFamilyLoader) {
        metricsFamilyLoaders.add(metricsFamilyLoader);
        return this;
    }

    public DashboardBuilder connectionSettings(ConnectionSettingable connectionSetting, Tag... tags) {
        environment.addConnectionMapping(connectionSetting, tags);
        return this;
    }

    public Dashboardable buildDashboard() {
        Dashboard dashboard = new Dashboard();
        dashboard.getEnvironments().addAll(environments);
        return dashboard;
    }

    public List<MetricsFamilyLoader> buildLoaders() {
        return Collections.unmodifiableList(metricsFamilyLoaders);
    }
}
