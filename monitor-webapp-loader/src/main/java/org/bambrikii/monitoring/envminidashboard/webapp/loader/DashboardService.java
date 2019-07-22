package org.bambrikii.monitoring.envminidashboard.webapp.loader;

import org.bambrikii.monitoring.envminidashboard.connectors.ConnectionPool;
import org.bambrikii.monitoring.envminidashboard.dashboard.DashboardLoader;
import org.bambrikii.monitoring.envminidashboard.impl.loader.LinuxSysMetricsLoader;
import org.bambrikii.monitoring.envminidashboard.impl.loader.WindowsAppLogsMetricsLoader;
import org.bambrikii.monitoring.envminidashboard.impl.loader.WindowsSysMetricsLoader;
import org.bambrikii.monitoring.envminidashboard.loaders.MetricsFamilyLoader;
import org.bambrikii.monitoring.envminidashboard.result.DashboardResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class DashboardService {
    @Autowired
    private DashboardConfigService dashboardConfigService;
    private ConnectionPool connectionPool;
    private List<MetricsFamilyLoader> loaders;
    private DashboardLoader loader;

    @PostConstruct
    public void init() {
        connectionPool = new ConnectionPool();
        loaders = Arrays.asList(new MetricsFamilyLoader[]{
                new LinuxSysMetricsLoader(),
                new WindowsSysMetricsLoader(),
                new WindowsAppLogsMetricsLoader()
        });
        loader = new DashboardLoader(connectionPool, loaders);
    }

    public DashboardResult readDashboardMetrics() {
        return loader.load(dashboardConfigService.retrieveConfigs()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException()));
    }
}
