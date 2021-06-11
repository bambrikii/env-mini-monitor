package org.bambrikii.monitoring.envminidashboard.model;

import org.bambrikii.monitoring.envminidashboard.model.api.PhysicalConnectable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PhysicalConn implements PhysicalConnectable<
        Tag,
        ConnConfig,
        MetricLog
        > {
    private List<Tag> tags;
    private ConnConfig config;
    private List<MetricLog> metricLogs = new ArrayList<>();

    @Override
    public List<Tag> getTags() {
        return tags;
    }

    @Override
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public ConnConfig getConfig() {
        return this.config;
    }

    @Override
    public void setConfig(ConnConfig config) {
        this.config = config;
    }

    @Override
    public List<MetricLog> getMetricLogs() {
        return metricLogs;
    }

    @Override
    public void setMetricLogs(List<MetricLog> metricLogs) {
        this.metricLogs = metricLogs;
    }

    @Override
    public void accept(Metric metric, Object value, Calendar time) {
        MetricLog log = new MetricLog();
        log.setMetric(metric);
        log.setValue(value);
        log.setTime(time);
        metricLogs.add(log);
    }

    @Override
    public void fail(Metric metric, String message, Calendar time) {
        MetricLog log = new MetricLog();
        log.setMetric(metric);
        log.setValue(message);
        log.setTime(time);
        metricLogs.add(log);
    }

    @Override
    public String toString() {
        return "PhysicalConn{" + "tags=" + tags + ", config=" + config + ", metricLogs=" + metricLogs + '}';
    }
}
