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
    private List<MetricsNamespaceResult> metrics = new ArrayList<>();

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    public String getConnectionName() {
        return connectionName;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    public List<MetricsNamespaceResult> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<MetricsNamespaceResult> metrics) {
        this.metrics = metrics;
    }
}
