package org.bambrikii.monitoring.envminidashboard.web.converters;

import org.bambrikii.monitoring.envminidashboard.model.Env;
import org.bambrikii.monitoring.envminidashboard.orm.model.EnvEntity;

import java.util.stream.Collectors;

public class EnvConverter {
    public static Env convert(EnvEntity entity) {
        Env env = new Env();
        env.setCode(entity.getCode());
        env.setConnections(entity
                .getConnections()
                .stream()
                .map(PhysicalConnConverter::convert)
                .collect(Collectors.toList())
        );
        return env;
    }

    public static EnvEntity convert(Env env) {
        EnvEntity entity = new EnvEntity();
        convert(env, entity);
        entity.setConnections(env
                .getConnections()
                .stream()
                .map(PhysicalConnConverter::convert)
                .collect(Collectors.toList()));
        return entity;
    }

    public static void convert(Env env, EnvEntity entity) {
        entity.setCode(env.getCode());
    }
}
