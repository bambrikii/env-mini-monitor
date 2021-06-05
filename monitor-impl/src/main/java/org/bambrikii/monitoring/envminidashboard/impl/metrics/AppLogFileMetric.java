package org.bambrikii.monitoring.envminidashboard.impl.metrics;

import lombok.Getter;
import lombok.Setter;
import org.bambrikii.monitoring.envminidashboard.result.Metric;

import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsRegistry.APP_LOGS_NAMESPACE;

@Getter
@Setter
public class AppLogFileMetric extends Metric {
    public AppLogFileMetric() {
        super("app.log.file", APP_LOGS_NAMESPACE);
    }
}
