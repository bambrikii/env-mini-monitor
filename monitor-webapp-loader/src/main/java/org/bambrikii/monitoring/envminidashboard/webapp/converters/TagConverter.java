package org.bambrikii.monitoring.envminidashboard.webapp.converters;

import org.bambrikii.monitoring.envminidashboard.data.config.TagEntity;
import org.bambrikii.monitoring.envminidashboard.model.Tag;

import java.util.stream.Collectors;

public class TagConverter {
    public static Tag convert(TagEntity entity) {
        Tag tag = new Tag();
        tag.setName(entity.getName());
        tag.setConnConfigs(entity
                .getConnConfigs()
                .stream()
                .map(ConnConfigConverter::convert)
                .collect(Collectors.toList()));
        ;
        return tag;
    }
}
