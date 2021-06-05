package org.bambrikii.monitoring.envminidashboard.webapp.converters;

import org.bambrikii.monitoring.envminidashboard.data.config.DashboardConfigEntity;
import org.bambrikii.monitoring.envminidashboard.data.config.EnvConfigEntity;
import org.bambrikii.monitoring.envminidashboard.model.Dashboard;

import java.util.List;
import java.util.stream.Collectors;

public class DashboardEntityConverter {
    public static Dashboard convert(DashboardConfigEntity entity) {
        Dashboard dashboard = new Dashboard();
        List<EnvConfigEntity> envs = entity.getEnvs();
        dashboard
                .setEnvs(envs
                        .stream()
                        .map(EnvConverter::convert)
                        .collect(Collectors.toList())
                );
        return null;
    }
}
