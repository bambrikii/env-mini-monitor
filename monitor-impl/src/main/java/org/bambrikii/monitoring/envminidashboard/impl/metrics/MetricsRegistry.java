package org.bambrikii.monitoring.envminidashboard.impl.metrics;

import org.bambrikii.monitoring.envminidashboard.connectors.AbstractProbe;
import org.bambrikii.monitoring.envminidashboard.model.Metric;

import java.util.ArrayList;
import java.util.List;

public class MetricsRegistry {
    public static final String SYS = "sys";
    public static final String APP_LOGS = "app.logs";

    public static final String SYS_CPU_USAGE = "sys.cpu.usage";
    public static final String SYS_MEM_USAGE = "sys.mem.usage";
    public static final String SYS_FILESYSTEM_USAGE = "sys.filesystem.usage";

    public static final SysMetricsNamespace SYS_NAMESPACE = new SysMetricsNamespace();
    public static final AppLogsMetricsNamespace APP_LOGS_NAMESPACE = new AppLogsMetricsNamespace();

    public static final Metric SYS_CPU_USAGE_METRIC = new Metric(SYS_CPU_USAGE, SYS_NAMESPACE);
    public static final Metric SYS_FILESYSTEM_USAGE_METRIC = new Metric(SYS_FILESYSTEM_USAGE, SYS_NAMESPACE);
    public static final Metric SYS_MEM_USAGE_METRIC = new Metric(SYS_MEM_USAGE, SYS_NAMESPACE);
    public static final Metric APP_LOGS_METRIC = new Metric(APP_LOGS, APP_LOGS_NAMESPACE);

    private final List<AbstractProbe> probes = new ArrayList<>();
    private final List<Metric> metrics = new ArrayList<>();

    public MetricsRegistry() {
        init();
    }

    private void init() {
    }

    public MetricsRegistry probe(AbstractProbe probe) {
        probes.add(probe);
        return this;
    }
}
