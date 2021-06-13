package org.bambrikii.monitoring.envminidashboard.webapp.converters;

import org.bambrikii.monitoring.envminidashboard.orm.model.DashboardEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.EnvEntity;
import org.bambrikii.monitoring.envminidashboard.model.Dashboard;

import java.util.List;
import java.util.stream.Collectors;

public class DashboardEntityConverter {
    public static Dashboard convert(DashboardEntity entity) {
        Dashboard dashboard = new Dashboard();
        List<EnvEntity> envs = entity.getEnvs();
        dashboard
                .setEnvs(envs
                        .stream()
                        .map(EnvConverter::convert)
                        .collect(Collectors.toList())
                );
        return null;
    }
}
