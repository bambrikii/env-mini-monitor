package org.bambrikii.monitoring.envminidashboard.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Monitor {
    private Metricable metric;
    private Object value;
}
