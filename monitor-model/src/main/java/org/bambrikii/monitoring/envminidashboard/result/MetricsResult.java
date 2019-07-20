package org.bambrikii.monitoring.envminidashboard.result;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MetricsResult {
    private Metric metric;
    private Object value;
}
