package org.bambrikii.monitoring.envminidashboard.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class ConnectionResult {
    private String connectionType;
    private String connectionName;
    private List<MetricsFamilyResult> metrics = new ArrayList<>();
}
