package org.bambrikii.monitoring.envminidashboard.result;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MetricsFamilyResult {
    private String code;
    private List<MetricsResult> metrics = new ArrayList<>();
}
