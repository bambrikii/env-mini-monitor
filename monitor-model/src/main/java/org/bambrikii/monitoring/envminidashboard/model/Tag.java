package org.bambrikii.monitoring.envminidashboard.model;

import lombok.Getter;
import lombok.Setter;
import org.bambrikii.monitoring.envminidashboard.model.api.Taggable;

@Getter
@Setter
public class Tag implements Taggable<MetricsNamespace, ConnConfig> {
    private String name;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tag{" + "name='" + name + '\'' + '}';
    }
}
