package org.bambrikii.monitoring.envminidashboard.impl.metrics;

import lombok.Getter;
import lombok.Setter;
import org.bambrikii.monitoring.envminidashboard.model.Metricable;

@Getter
@Setter
public class ProcUsageMetric implements Metricable {
    @Override
    public String getCode() {
        return "system.cpu.usage";
    }
}
