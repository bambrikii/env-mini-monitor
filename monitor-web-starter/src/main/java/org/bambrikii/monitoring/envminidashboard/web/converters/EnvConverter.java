package org.bambrikii.monitoring.envminidashboard.web.converters;

import org.bambrikii.monitoring.envminidashboard.model.Env;
import org.bambrikii.monitoring.envminidashboard.model.PhysicalConn;
import org.bambrikii.monitoring.envminidashboard.orm.model.EnvEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.PhysicalConnEntity;

import java.util.function.Function;
import java.util.stream.Collectors;

public class EnvConverter {
    public static Env convert(EnvEntity entity) {
        Env env = new Env();
        convert(entity, env);
        return env;
    }

    public static void convert(EnvEntity entity, Env pojo) {
        pojo.setId(entity.getId());
        pojo.setCode(entity.getCode());
        pojo.setConnections(entity
                .getConnections()
                .stream()
                .map(PhysicalConnConverter::convert)
                .collect(Collectors.toList())
        );
    }

    public static void convert(Env pojo, EnvEntity entity, Function<PhysicalConn, PhysicalConnEntity> physicalConnMapper) {
        entity.setCode(pojo.getCode());
        entity.setConnections(pojo.getConnections()
                .stream()
                .map(physicalConnMapper)
                .collect(Collectors.toList())
        );
    }
}
