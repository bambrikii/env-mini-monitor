package org.bambrikii.monitoring.envminidashboard.webapp.loader;

import org.bambrikii.monitoring.envminidashboard.data.config.DashboardConfigEntity;
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

    public void save(DashboardConfigEntity dashboardConfigEntity) {
        dashboardConfigRepository.save(dashboardConfigEntity);
    }

    public List<DashboardConfigEntity> retrieveConfigs() {
        Iterable<DashboardConfigEntity> dashboardConfigs = dashboardConfigRepository.findAll();
        return StreamSupport
                .stream(dashboardConfigs.spliterator(), false)
                .collect(Collectors.toList());
    }
}
