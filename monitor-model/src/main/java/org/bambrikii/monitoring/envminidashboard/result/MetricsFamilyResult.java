package org.bambrikii.monitoring.envminidashboard.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class MetricsFamilyResult {
    private String code;
    private List<MetricsResult> metrics = new ArrayList<>();
}
