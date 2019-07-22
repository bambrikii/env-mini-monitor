package org.bambrikii.monitoring.envminidashboard.model;

import java.util.List;

public interface Taggable {
    String getName();

    List<MetricsFamilible> getMetricsFamilies();

    List<ConnectionSettingable> getConnectionSettings();
}
