package org.bambrikii.monitoring.envminidashboard.impl.loader;

import org.bambrikii.monitoring.envminidashboard.connectors.AbstractProbe;
import org.bambrikii.monitoring.envminidashboard.connectors.api.ProbeAction;
import org.bambrikii.monitoring.envminidashboard.connectors.api.ProbeResultCollector;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.windows.SmbConnector;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.windows.WinConnConfig;
import org.bambrikii.monitoring.envminidashboard.impl.metrics.FileSystemMetricsValue;
import org.bambrikii.monitoring.envminidashboard.impl.metrics.MemValue;
import org.bambrikii.monitoring.envminidashboard.model.Metric;
import org.bambrikii.monitoring.envminidashboard.model.api.ConnConfiggable;

import java.io.File;

import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsRegistry.SYS_CPU_USAGE_METRIC;
import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsRegistry.SYS_FILESYSTEM_USAGE;
import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsRegistry.SYS_MEM_USAGE_METRIC;
import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsRegistry.SYS_NAMESPACE;

public class WinSysProbe extends AbstractProbe<WinConnConfig, SmbConnector> {
    public static final ProbeAction CPU_ACTION = cmd -> Runtime.getRuntime().availableProcessors();
    public static final ProbeAction MEM_ACTION = cmd -> {
        Runtime runtime = Runtime.getRuntime();
        return new MemValue(
                runtime.freeMemory(),
                runtime.maxMemory(),
                runtime.totalMemory()
        );
    };

    public WinSysProbe() {
        super(SYS_NAMESPACE);
    }

    protected boolean isSupported(ConnConfiggable connectionSetting) {
        return connectionSetting instanceof WinConnConfig;
    }

    @Override
    protected void load(SmbConnector conn, ProbeResultCollector collector) {
        collector.accept(SYS_CPU_USAGE_METRIC, conn.perform(CPU_ACTION), getTime());
        collector.accept(SYS_MEM_USAGE_METRIC, conn.perform(MEM_ACTION), getTime());

        for (File root : File.listRoots()) {
            collector.accept(
                    new Metric(SYS_FILESYSTEM_USAGE + "." + root.getName(), SYS_NAMESPACE),
                    (ProbeAction) cmd -> {
                        Runtime runtime = Runtime.getRuntime();
                        return new FileSystemMetricsValue(
                                root.getAbsolutePath(),
                                root.getTotalSpace(),
                                root.getFreeSpace(),
                                root.getUsableSpace()
                        );
                    },
                    getTime()
            );
        }
    }
}
