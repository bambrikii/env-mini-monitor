package org.bambrikii.monitoring.envminidashboard.loaderlib;

import org.bambrikii.monitoring.envminidashboard.data.config.DashboardConfig;
import org.bambrikii.monitoring.envminidashboard.data.config.DashboardConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DashboardConfigService {
    @Autowired
    private DashboardConfigRepository dashboardConfigRepository;

    public void save(DashboardConfig dashboardConfig) {
        dashboardConfigRepository.save(dashboardConfig);
    }

    public List<DashboardConfig> retrieveConfigs() {
        Iterable<DashboardConfig> dashboardConfigs = dashboardConfigRepository.findAll();
        return StreamSupport
                .stream(dashboardConfigs.spliterator(), false)
                .collect(Collectors.toList());
    }
}
