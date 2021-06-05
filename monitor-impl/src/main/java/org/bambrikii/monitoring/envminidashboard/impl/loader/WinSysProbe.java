package org.bambrikii.monitoring.envminidashboard.impl.loader;

import org.bambrikii.monitoring.envminidashboard.connectors.AbstractProbe;
import org.bambrikii.monitoring.envminidashboard.connectors.ProbeResultCollector;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.windows.SmbConnector;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.windows.WinConnConfig;
import org.bambrikii.monitoring.envminidashboard.impl.metrics.FileSystemMetricsValue;
import org.bambrikii.monitoring.envminidashboard.model.ConnConfig;
import org.bambrikii.monitoring.envminidashboard.result.Metric;

import java.io.File;
import java.util.Arrays;

import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsRegistry.SYS_CPU_USAGE_METRIC;
import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsRegistry.SYS_FILESYSTEM_USAGE;
import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsRegistry.SYS_MEM_USAGE_METRIC;
import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsRegistry.SYS_NAMESPACE;

public class WinSysProbe extends AbstractProbe<WinConnConfig, SmbConnector> {
    public WinSysProbe() {
        super(SYS_NAMESPACE);
    }

    protected boolean isSupported(ConnConfig connectionSetting) {
        return connectionSetting instanceof WinConnConfig;
    }

    @Override
    protected void load(SmbConnector conn, ProbeResultCollector collector) {
        collector.accept(
                SYS_CPU_USAGE_METRIC,
                conn.perform(cmd -> Runtime.getRuntime().availableProcessors())
        );

        collector.accept(
                SYS_MEM_USAGE_METRIC,
                conn.perform(cmd -> Runtime.getRuntime().availableProcessors())
        );
        Arrays.asList(File.listRoots())
                .stream()
                .forEach(root ->
                        collector.accept(
                                new Metric(SYS_FILESYSTEM_USAGE + "." + root.getName(), SYS_NAMESPACE),
                                new FileSystemMetricsValue(
                                        root.getAbsolutePath(),
                                        root.getTotalSpace(),
                                        root.getFreeSpace(),
                                        root.getUsableSpace()
                                )
                        )
                );
    }
}
