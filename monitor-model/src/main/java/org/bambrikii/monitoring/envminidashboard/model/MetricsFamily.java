package org.bambrikii.monitoring.envminidashboard.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MetricsFamily implements MetricsFamilible {
    @Setter(AccessLevel.NONE)
    private String code;
}
