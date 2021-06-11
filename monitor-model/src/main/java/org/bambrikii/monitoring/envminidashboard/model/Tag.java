package org.bambrikii.monitoring.envminidashboard.model;

import lombok.Getter;
import lombok.Setter;
import org.bambrikii.monitoring.envminidashboard.model.api.Taggable;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Tag implements Taggable<MetricsNamespace, ConnConfig> {
    private String name;
    private List<ConnConfig> connConfigs = new ArrayList<>();

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<ConnConfig> getConnConfigs() {
        return connConfigs;
    }

    public void setConnConfigs(List<ConnConfig> connConfigs) {
        this.connConfigs = connConfigs;
    }

    @Override
    public String toString() {
        return "Tag{" + "name='" + name + '\'' + ", connConfigs=" + connConfigs + '}';
    }
}
