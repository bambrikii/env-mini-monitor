package org.bambrikii.monitoring.envminidashboard.web.converters;

import org.bambrikii.monitoring.envminidashboard.model.Dashboard;
import org.bambrikii.monitoring.envminidashboard.model.Env;
import org.bambrikii.monitoring.envminidashboard.orm.model.DashboardEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.EnvEntity;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DashboardConverter {
    public static Dashboard convert(DashboardEntity entity) {
        Dashboard pojo = new Dashboard();
        convert(entity, pojo);
        return pojo;
    }

    private static void convert(DashboardEntity entity, Dashboard pojo) {
        pojo.setId(entity.getId());
        pojo.setName(entity.getName());
        pojo.setEnvs(entity.getEnvs()
                .stream()
                .map(EnvConverter::convert)
                .collect(Collectors.toList())
        );
    }

    public static void convert(Dashboard pojo, DashboardEntity entity, Function<Env, EnvEntity> envMapper) {
        entity.setId(pojo.getId());
        entity.setName(pojo.getName());
        List<Env> envs = pojo.getEnvs();
        if (envs != null && envMapper != null) {
            entity.setEnvs(envs
                    .stream()
                    .map(envMapper::apply)
                    .collect(Collectors.toList())
            );
        }
    }
}
