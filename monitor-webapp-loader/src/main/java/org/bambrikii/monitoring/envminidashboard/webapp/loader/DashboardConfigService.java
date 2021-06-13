package org.bambrikii.monitoring.envminidashboard.webapp.loader;

import org.bambrikii.monitoring.envminidashboard.orm.model.DashboardEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DashboardConfigService {
    @Autowired
    private DashboardRepository dashboardRepository;

    public void save(DashboardEntity dashboardEntity) {
        dashboardRepository.save(dashboardEntity);
    }

    public List<DashboardEntity> retrieveConfigs() {
        Iterable<DashboardEntity> dashboardConfigs = dashboardRepository.findAll();
        return StreamSupport
                .stream(dashboardConfigs.spliterator(), false)
                .collect(Collectors.toList());
    }
}
