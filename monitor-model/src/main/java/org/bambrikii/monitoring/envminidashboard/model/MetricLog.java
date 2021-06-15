package org.bambrikii.monitoring.envminidashboard.model;

import org.bambrikii.monitoring.envminidashboard.model.api.MetricLoggable;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MetricLog implements MetricLoggable {
    public static final SimpleDateFormat DATE_TIME_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd.sss");
    private Long id;
    private Metric metric;
    private Object value;
    private Calendar time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MetricLog{" +
                "id=" + id +
                ", metric=" + metric +
                ", value=" + value +
                ", time=" + time +
                '}';
    }
}
