package org.bambrikii.monitoring.envminidashboard.model.api;

import org.bambrikii.monitoring.envminidashboard.connectors.api.ProbeResultCollector;

import java.util.List;

public interface PhysicalConnectable<
        T extends Taggable,
        C extends ConnConfiggable,
        M extends MetricLoggable
        > extends ProbeResultCollector {
    List<T> getTags();

    void setTags(List<T> tags);

    C getConfig();

    void setConfig(C config);

    List<M> getMetricLogs();

    void setMetricLogs(List<M> metricLogs);
}
