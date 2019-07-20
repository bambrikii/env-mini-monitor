package org.bambrikii.monitoring.envminidashboard.impl.metrics;

import org.bambrikii.monitoring.envminidashboard.result.Metric;

public class MetricsFactory {
    public static final SysMetricsFamily SYS_METRICS_FAMILY = new SysMetricsFamily();
    public static final AppLogsMetricsFamily APP_LOGS_METRICS_FAMILY = new AppLogsMetricsFamily();

    public static final Metric SYS_CPU_USAGE_METRIC = new Metric("sys.cpu.usage");
    public static final Metric SYS_MEM_USAGE_METRIC = new Metric("sys.mem.usage");

    public static final Metric APP_LOGS_METRIC = new Metric("app.logs");
}
