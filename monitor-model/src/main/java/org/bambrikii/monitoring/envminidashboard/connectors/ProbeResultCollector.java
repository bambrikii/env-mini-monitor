package org.bambrikii.monitoring.envminidashboard.connectors;

import org.bambrikii.monitoring.envminidashboard.result.Metric;

public interface ProbeResultCollector {
    void accept(Metric metric, Object value);

    void fail(String code, String message);
}
