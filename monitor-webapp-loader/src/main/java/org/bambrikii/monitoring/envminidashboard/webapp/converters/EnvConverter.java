package org.bambrikii.monitoring.envminidashboard.webapp.converters;

import org.bambrikii.monitoring.envminidashboard.data.config.EnvEntity;
import org.bambrikii.monitoring.envminidashboard.model.Env;

import java.util.stream.Collectors;

public class EnvConverter {
    public static Env convert(EnvEntity entity) {
        Env env = new Env();
        env.setCode(entity.getCode());
        env.setConnections(entity
                .getConnections()
                .stream()
                .map(PhysicalConnectionConverter::convert)
                .collect(Collectors.toList())
        );
        return env;
    }
}
