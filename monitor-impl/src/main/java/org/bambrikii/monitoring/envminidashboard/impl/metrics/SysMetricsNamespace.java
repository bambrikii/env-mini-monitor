package org.bambrikii.monitoring.envminidashboard.impl.metrics;

import org.bambrikii.monitoring.envminidashboard.model.MetricsNamespace;

import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsRegistry.SYS;

public class SysMetricsNamespace extends MetricsNamespace {
    public SysMetricsNamespace() {
        super(SYS);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
