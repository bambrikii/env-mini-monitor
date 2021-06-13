package org.bambrikii.monitoring.envminidashboard.model.api;

public interface Taggable<
        M extends MetricsNamespaceable,
        C extends ConnConfiggable
        > {
    String getName();
}
