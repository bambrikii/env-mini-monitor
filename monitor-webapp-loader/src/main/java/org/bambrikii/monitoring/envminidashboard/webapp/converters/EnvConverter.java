package org.bambrikii.monitoring.envminidashboard.webapp.converters;

import org.bambrikii.monitoring.envminidashboard.data.config.EnvConfigEntity;
import org.bambrikii.monitoring.envminidashboard.model.Env;

import java.util.stream.Collectors;

public class EnvConverter {
    public static Env convert(EnvConfigEntity entity) {
        Env env = new Env();
        env.setCode(entity.getCode());
        env.setTags(entity
                .getTags()
                .stream()
                .map(TagConverter::convert)
                .collect(Collectors.toList())
        );
        return env;
    }
}
