package org.bambrikii.monitoring.envminidashboard.connectors;

import org.bambrikii.monitoring.envminidashboard.result.MetricsResult;

import java.util.List;
import java.util.function.Function;

public interface ConnectorCommandable<T, R> {
    List<MetricsResult> execute(Function<T, R> command);
}
