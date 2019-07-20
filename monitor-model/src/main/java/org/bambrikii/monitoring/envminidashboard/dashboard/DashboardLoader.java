package org.bambrikii.monitoring.envminidashboard.dashboard;

import org.bambrikii.monitoring.envminidashboard.connectors.ConnectionPool;
import org.bambrikii.monitoring.envminidashboard.loaders.MetricsFamilyLoader;
import org.bambrikii.monitoring.envminidashboard.model.*;
import org.bambrikii.monitoring.envminidashboard.result.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class DashboardLoader {
    private final ConnectionPool connectionPool;

    public DashboardLoader(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public DashboardResult load(Dashboard dashboard) {
        DashboardResult dashboardResult = new DashboardResult();
        Map<MetricsFamily, List<MetricsFamilyLoader>> metricsLoaders = dashboard.getMetricsFamilyLoadersByMetricsFamily();
        for (Environment environment : dashboard.getEnvironments()) {
            String envCode = environment.getCode();

            EnvironmentResult environmentResult = dashboardResult.addEnvironment(envCode);

            Map<Tag, TagMappingContainer> tags = environment.getTagMappingContainers();
            for (Map.Entry<Tag, TagMappingContainer> tagEntry : tags.entrySet()) {
                Tag tag = tagEntry.getKey();
                TagMappingContainer tagMappingContainer = tagEntry.getValue();
                Set<MetricsFamily> metricsFamilies = tagMappingContainer.getMetricsFamilies();
                Set<ConnectionSetting> connectionSettings = tagMappingContainer.getConnectionSettings();

                ConnectionResult connectionResult = environmentResult.addConnection();
                connectionResult.getTags().add(tag);

                for (MetricsFamily metricsFamily : metricsFamilies) {
                    List<MetricsFamilyLoader> metricsFamilyLoaders = metricsLoaders.get(metricsFamily);
                    if (metricsFamilyLoaders == null || metricsFamilyLoaders.isEmpty()) {
                        continue;
                    }

                    MetricsFamilyResult metricsFamilyResult = new MetricsFamilyResult();
                    metricsFamilyResult.setCode(metricsFamily.getCode());
                    connectionResult.getMetrics().add(metricsFamilyResult);

                    for (MetricsFamilyLoader metricsFamilyLoader : metricsFamilyLoaders) {
                        for (ConnectionSetting connectionSetting : connectionSettings) {
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
