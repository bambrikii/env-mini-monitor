package org.bambrikii.monitoring.envminidashboard.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bambrikii.monitoring.envminidashboard.connectors.ProbeResultCollector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class DashboardResult implements ProbeResultCollector {
    private final Map<Metric, Object> values = new HashMap<>();
    private final List<String> errors = new ArrayList<>();
    private Map<String, EnvironmentResult> envs = new HashMap<>();

    private EnvironmentResult env;
    private TagResult tag;

    @Override
    public void accept(Metric metric, Object value) {
        tag.metric(metric, value);
    }

    @Override
    public void fail(String code, String message) {
        errors.add(code + ": " + message);
    }

    public void env(String envCode) {
        if (!envs.containsKey(envCode)) {
            EnvironmentResult env = new EnvironmentResult();
            envs.put(envCode, env);
            this.env = env;
        }
    }

    public void tag(String tagName) {
        tag = env.addTag(tagName);
    }
}

