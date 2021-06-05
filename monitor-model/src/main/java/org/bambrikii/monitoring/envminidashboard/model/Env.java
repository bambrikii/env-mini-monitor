package org.bambrikii.monitoring.envminidashboard.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Env implements Environmentable {
    private String code;

    @Setter(AccessLevel.NONE)
    private List<Taggable> tags = new ArrayList<>();

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private final Map<String, Taggable> tagMap = new HashMap<>();

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public List<Taggable> getTags() {
        return tags;
    }

    public void setTags(List<Taggable> tags) {
        this.tags = tags;
    }

    public Environmentable tag(MetricsNamespaceable namespace, Taggable... tags) {
        Arrays.stream(tags).forEach(tag -> ensureTag(tag)
                .getMetricsNamespaces()
                .add(namespace));
        return this;
    }

    public Environmentable tag(ConnConfig connConfig, Taggable... tags) {
        Arrays.stream(tags).forEach(tag -> ensureTag(tag)
                .getConnConfigs()
                .add(connConfig));
        return this;
    }

    private Taggable ensureTag(Taggable tag) {
        String tagName = tag.getName();
        if (tagMap.containsKey(tagName)) {
            return tagMap.get(tagName);
        }
        tagMap.put(tagName, tag);
        tags.add(tag);
        return tag;
    }
}
