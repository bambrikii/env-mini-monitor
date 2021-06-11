package org.bambrikii.monitoring.envminidashboard.model.api;

import java.util.List;

public interface Taggable<
        M extends MetricsNamespaceable,
        C extends ConnConfiggable
        > {
    String getName();

    List<C> getConnConfigs();
}
