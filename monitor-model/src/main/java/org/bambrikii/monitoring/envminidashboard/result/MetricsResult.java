package org.bambrikii.monitoring.envminidashboard.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MetricsResult {
    private Metric metric;
    private Object value;
}
