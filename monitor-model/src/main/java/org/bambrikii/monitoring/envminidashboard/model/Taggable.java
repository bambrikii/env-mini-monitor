package org.bambrikii.monitoring.envminidashboard.model;

import java.util.List;

public interface Taggable<M extends MetricsNamespaceable, C extends ConnConfig> {
    String getName();

    List<M> getMetricsNamespaces();

    List<C> getConnConfigs();
}
