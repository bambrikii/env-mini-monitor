package org.bambrikii.monitoring.envminidashboard.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class EnvironmentResult {
    private String code;
    private List<TagResult> tags = new ArrayList<>();

    public TagResult addTag(String name) {
        TagResult tagResult = new TagResult();
        tagResult.setName(name);

        tags.add(tagResult);

        return tagResult;
    }
}
