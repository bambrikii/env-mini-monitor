package org.bambrikii.monitoring.envminidashboard.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public class TagResult {
    private String name;
    private Map<Metric, Object> values = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Metric, Object> getValues() {
        return values;
    }

    public void setValues(Map<Metric, Object> values) {
        this.values = values;
    }

    public void metric(Metric metric, Object value) {
        values.put(metric, value);
    }
}
