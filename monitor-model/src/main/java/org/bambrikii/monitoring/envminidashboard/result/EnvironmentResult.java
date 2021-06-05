package org.bambrikii.monitoring.envminidashboard.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public class EnvironmentResult {
    private String code;
    private Map<String, TagResult> tags = new HashMap<>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map<String, TagResult> getTags() {
        return tags;
    }

    public void setTags(Map<String, TagResult> tags) {
        this.tags = tags;
    }

    public TagResult addTag(String name) {
        TagResult tag = new TagResult();
        tag.setName(name);

        if (!tags.containsKey(name)) {
            tags.put(name, tag);
        }

        return tag;
    }
}
