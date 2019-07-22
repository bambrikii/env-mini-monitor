package org.bambrikii.monitoring.envminidashboard.dashboard;

import org.bambrikii.monitoring.envminidashboard.connectors.ConnectionPool;
import org.bambrikii.monitoring.envminidashboard.loaders.MetricsFamilyLoader;
import org.bambrikii.monitoring.envminidashboard.model.ConnectionSettingable;
import org.bambrikii.monitoring.envminidashboard.model.Dashboardable;
import org.bambrikii.monitoring.envminidashboard.model.Environmentable;
import org.bambrikii.monitoring.envminidashboard.model.MetricsFamilible;
import org.bambrikii.monitoring.envminidashboard.model.Taggable;
import org.bambrikii.monitoring.envminidashboard.result.ConnectionResult;
import org.bambrikii.monitoring.envminidashboard.result.DashboardResult;
import org.bambrikii.monitoring.envminidashboard.result.EnvironmentResult;
import org.bambrikii.monitoring.envminidashboard.result.MetricsFamilyResult;
import org.bambrikii.monitoring.envminidashboard.result.MetricsResult;
import org.bambrikii.monitoring.envminidashboard.result.TagResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashboardLoader {
    private final ConnectionPool connectionPool;
    private final Map<MetricsFamilible, List<MetricsFamilyLoader>> metricsLoaders;

    public DashboardLoader(ConnectionPool connectionPool, List<MetricsFamilyLoader> metricsFamilyLoaders) {
        this.connectionPool = connectionPool;
        Map<MetricsFamilible, List<MetricsFamilyLoader>> metricsLoaders = new HashMap<>();
        for (MetricsFamilyLoader loader : metricsFamilyLoaders) {
            List<MetricsFamilible> metricsFamilies = loader.getMetricsFamilies();
            for (MetricsFamilible family : metricsFamilies) {
                metricsLoaders.computeIfAbsent(family, metricsFamily -> new ArrayList<>());
                metricsLoaders.get(family).add(loader);
            }
        }
        this.metricsLoaders = Collections.unmodifiableMap(metricsLoaders);
    }

    public DashboardResult load(Dashboardable dashboard) {
        DashboardResult dashboardResult = new DashboardResult();

        for (Environmentable environment : dashboard.getEnvironments()) {
            String envCode = environment.getCode();

            EnvironmentResult environmentResult = dashboardResult.addEnvironment(envCode);

            List<Taggable> tags = environment.getTags();
            for (Taggable tag : tags) {
                List<MetricsFamilible> metricsFamilies = tag.getMetricsFamilies();
                List<ConnectionSettingable> connectionSettings = tag.getConnectionSettings();

                TagResult tagResult = environmentResult.addTag(tag.getName());

                for (ConnectionSettingable connectionSetting : connectionSettings) {

                    ConnectionResult connectionResult = tagResult.addConnection(connectionSetting.getClass().getSimpleName(), connectionSetting.getHost() + ":" + connectionSetting.getPort());

                    for (MetricsFamilible metricsFamily : metricsFamilies) {
                        List<MetricsFamilyLoader> metricsFamilyLoaders = metricsLoaders.get(metricsFamily);
                        if (metricsFamilyLoaders == null || metricsFamilyLoaders.isEmpty()) {
                            continue;
                        }

                        MetricsFamilyResult metricsFamilyResult = new MetricsFamilyResult();
                        metricsFamilyResult.setCode(metricsFamily.getCode());
                        connectionResult.getMetrics().add(metricsFamilyResult);

                        for (MetricsFamilyLoader metricsFamilyLoader : metricsFamilyLoaders) {
                            List<MetricsResult> metricsResults = metricsFamilyLoader.load(connectionPool, connectionSetting, tag);

                            metricsFamilyResult.getMetrics().addAll(metricsResults);
                        }
                    }
                }
            }
        }
        return dashboardResult;
    }
}
