package org.bambrikii.monitoring.envminidashboard.webapp.loader;

import org.bambrikii.monitoring.envminidashboard.connectors.ConnectionPool;
import org.bambrikii.monitoring.envminidashboard.impl.dashboard.DashboardLoader;
import org.bambrikii.monitoring.envminidashboard.impl.loader.LinuxSysProbe;
import org.bambrikii.monitoring.envminidashboard.impl.loader.WindowsAppLogsProbe;
import org.bambrikii.monitoring.envminidashboard.impl.loader.WinSysProbe;
import org.bambrikii.monitoring.envminidashboard.connectors.AbstractProbe;
import org.bambrikii.monitoring.envminidashboard.result.DashboardResult;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DashboardService {
    private final DashboardConfigService dashboardConfigService;
    private final ConnectionPool connectionPool = new ConnectionPool();
    private final List<AbstractProbe> loaders = Arrays.asList(new AbstractProbe[]{
            new LinuxSysProbe(),
            new WinSysProbe(),
            new WindowsAppLogsProbe()
    });
    private final DashboardLoader loader = new DashboardLoader(connectionPool, loaders);

    public DashboardService(DashboardConfigService dashboardConfigService) {
        this.dashboardConfigService = dashboardConfigService;
    }

    public DashboardResult readDashboardMetrics() {
        return loader.load(dashboardConfigService.retrieveConfigs()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException()));
    }
}
