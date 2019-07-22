package org.bambrikii.monitoring.envminidashboard.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Environment implements Environmentable {
    private String code;

    @Setter(AccessLevel.NONE)
    private List<Taggable> tags = new ArrayList<>();

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Map<String, Taggable> tagMappingContainerMap = new HashMap<>();

    public Environmentable addMetricsFamily(MetricsFamilible metricsFamily, Taggable... tags) {
        for (Taggable tag : tags) {
            ensureTag(tag).getMetricsFamilies().add(metricsFamily);
        }
        return this;
    }

    public Environmentable addConnectionMapping(ConnectionSettingable connectionSetting, Taggable... tags) {
        for (Taggable tag : tags) {
            ensureTag(tag).getConnectionSettings().add(connectionSetting);
        }
        return this;
    }

    private Taggable ensureTag(Taggable tag) {
        String tagName = tag.getName();
        if (!tagMappingContainerMap.containsKey(tagName)) {
            Tag container = new Tag();
            tagMappingContainerMap.put(tagName, container);
            tags.add(container);
            return container;
        }
        return tagMappingContainerMap.get(tagName);
    }
}
