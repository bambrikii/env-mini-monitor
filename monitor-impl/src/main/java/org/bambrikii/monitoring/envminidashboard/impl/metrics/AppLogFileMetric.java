package org.bambrikii.monitoring.envminidashboard.impl.metrics;

import lombok.Getter;
import lombok.Setter;
import org.bambrikii.monitoring.envminidashboard.result.Metric;

@Getter
@Setter
public class AppLogFileMetric extends Metric {
    public AppLogFileMetric() {
        super("app.log.file");
    }
}
