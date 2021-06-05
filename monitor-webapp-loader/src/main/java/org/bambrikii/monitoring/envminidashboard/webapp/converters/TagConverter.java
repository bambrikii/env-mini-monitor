package org.bambrikii.monitoring.envminidashboard.webapp.converters;

import org.bambrikii.monitoring.envminidashboard.data.config.TagConfigEntity;
import org.bambrikii.monitoring.envminidashboard.model.Tag;

import java.util.stream.Collectors;

public class TagConverter {
    public static Tag convert(TagConfigEntity entity) {
        Tag tag = new Tag();
        tag.setName(entity.getName());
        tag.setMetricsNamespaces(entity
                .getMetricsNamespaces()
                .stream()
                .map(MetricsNamespaceConverter::convert)
                .collect(Collectors.toList()))
        ;
        tag.setConnConfigs(entity
                .getConnConfigs()
                .stream()
                .map(ConnConfigConverter::convert)
                .collect(Collectors.toList()));
        ;
        return tag;
    }
}
