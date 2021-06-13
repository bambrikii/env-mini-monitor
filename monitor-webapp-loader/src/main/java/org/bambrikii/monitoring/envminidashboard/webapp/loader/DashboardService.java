package org.bambrikii.monitoring.envminidashboard.webapp.loader;

import org.bambrikii.monitoring.envminidashboard.orm.model.DashboardEntity;
import org.bambrikii.monitoring.envminidashboard.impl.dashboard.DashboardLoader;
import org.bambrikii.monitoring.envminidashboard.impl.loader.LinuxSysProbe;
import org.bambrikii.monitoring.envminidashboard.impl.loader.WinSysProbe;
import org.bambrikii.monitoring.envminidashboard.impl.loader.WindowsAppLogsProbe;
import org.bambrikii.monitoring.envminidashboard.model.Dashboard;
import org.bambrikii.monitoring.envminidashboard.webapp.converters.DashboardEntityConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    private final DashboardConfigService dashboardConfigService;
    private final DashboardLoader loader = new DashboardLoader()
            .probe(new LinuxSysProbe())
            .probe(new WinSysProbe())
            .probe(new WindowsAppLogsProbe());

    public DashboardService(DashboardConfigService dashboardConfigService) {
        this.dashboardConfigService = dashboardConfigService;
    }

    public Dashboard readDashboardMetrics() {
        List<DashboardEntity> configEntities = dashboardConfigService.retrieveConfigs();

        return loader.load(configEntities
                .stream()
                .map(DashboardEntityConverter::convert)
                .findFirst()
                .orElseThrow(() -> new RuntimeException()));
    }
}
