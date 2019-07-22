package org.bambrikii.monitoring.envminidashboard.model;

import java.util.List;

public interface Taggable<M extends MetricsFamilible, C extends ConnectionSettingable> {
    String getName();

    List<M> getMetricsFamilies();

    List<C> getConnectionSettings();
}
