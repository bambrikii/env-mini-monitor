package org.bambrikii.monitoring.envminidashboard.web.converters;

import org.bambrikii.monitoring.envminidashboard.model.Dashboard;
import org.bambrikii.monitoring.envminidashboard.model.Env;
import org.bambrikii.monitoring.envminidashboard.orm.model.DashboardEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.EnvEntity;

import java.util.List;
import java.util.stream.Collectors;

public class DashboardConverter {
    public static Dashboard convert(DashboardEntity entity) {
        Dashboard pojo = new Dashboard();
        List<EnvEntity> envs = entity.getEnvs();
        pojo
                .setEnvs(envs
                        .stream()
                        .map(EnvConverter::convert)
                        .collect(Collectors.toList())
                );
        return null;
    }

    public static DashboardEntity convert(Dashboard pojo) {
        DashboardEntity entity = new DashboardEntity();
        convert(pojo, entity);
        List<Env> envs = pojo.getEnvs();
        entity
                .setEnvs(envs
                        .stream()
                        .map(EnvConverter::convert)
                        .collect(Collectors.toList())
                );
        return entity;
    }

    public static void convert(Dashboard pojo, DashboardEntity entity) {

    }
}
