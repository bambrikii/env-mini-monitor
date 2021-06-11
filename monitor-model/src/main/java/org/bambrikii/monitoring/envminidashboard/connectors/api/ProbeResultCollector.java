package org.bambrikii.monitoring.envminidashboard.connectors.api;

import org.bambrikii.monitoring.envminidashboard.model.Metric;

import java.util.Calendar;

public interface ProbeResultCollector {
    void accept(Metric metric, Object value, Calendar time);

    void fail(Metric metric, String message, Calendar time);
}
