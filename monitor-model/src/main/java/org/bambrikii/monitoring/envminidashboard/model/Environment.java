package org.bambrikii.monitoring.envminidashboard.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
public class Environment {
    private String code;
    @Setter(AccessLevel.NONE)
    private Map<Tag, TagMappingContainer> tagMappingContainers = new LinkedHashMap<>();

    public Environment addMetricsFamily(MetricsFamily metricsFamily, Tag... tags) {
        for (Tag tag : tags) {
            ensureTagMappingContainer(tag).ensureMetricsFamily(metricsFamily);
        }
        return this;
    }

    public Environment addConnectionMapping(ConnectionSetting connectionSetting, Tag... tags) {
        for (Tag tag : tags) {
            ensureTagMappingContainer(tag).ensureConnectionSettings(connectionSetting);
        }
        return this;
    }

    private TagMappingContainer ensureTagMappingContainer(Tag tag) {
        return tagMappingContainers.computeIfAbsent(tag, (tag1) -> new TagMappingContainer());
    }
}
