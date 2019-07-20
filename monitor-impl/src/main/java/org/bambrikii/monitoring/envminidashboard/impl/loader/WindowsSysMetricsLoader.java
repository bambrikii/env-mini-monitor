package org.bambrikii.monitoring.envminidashboard.impl.loader;

import org.bambrikii.monitoring.envminidashboard.connectors.ConnectorCommandable;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.windows.WindowsConnectionSetting;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.windows.WindowsConnector;
import org.bambrikii.monitoring.envminidashboard.impl.metrics.FileSystemMetricsValue;
import org.bambrikii.monitoring.envminidashboard.loaders.MetricsFamilyLoader;
import org.bambrikii.monitoring.envminidashboard.model.ConnectionSetting;
import org.bambrikii.monitoring.envminidashboard.result.Metric;
import org.bambrikii.monitoring.envminidashboard.result.MetricsResult;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsFactory.SYS_CPU_USAGE_METRIC;
import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsFactory.SYS_FILESYSTEM_USAGE_METRIC;
import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsFactory.SYS_MEM_USAGE_METRIC;
import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsFactory.SYS_METRICS_FAMILY;

public class WindowsSysMetricsLoader extends MetricsFamilyLoader<WindowsConnectionSetting, WindowsConnector> {
    public WindowsSysMetricsLoader() {
        super(SYS_METRICS_FAMILY);
    }

    @Override
    protected ConnectorCommandable[] getCommands() {
        return new ConnectorCommandable[]{
                (ConnectorCommandable<Object, Object>) command -> Arrays.asList(new MetricsResult(
                        new Metric(SYS_CPU_USAGE_METRIC.getCode()),
                        command.apply(Runtime.getRuntime().availableProcessors())
                )),
                (ConnectorCommandable<Object, Object>) command -> Arrays.asList(new MetricsResult(
                        new Metric(SYS_MEM_USAGE_METRIC.getCode()),
                        command.apply(Runtime.getRuntime().availableProcessors())
                )),
                (ConnectorCommandable<Object, Object>) command ->
                        Arrays.asList(File.listRoots())
                                .stream()
                                .map(root ->
                                        new MetricsResult(
                                                new Metric(SYS_FILESYSTEM_USAGE_METRIC.getCode() + "." + root.getName()),
                                                new FileSystemMetricsValue(root.getAbsolutePath(),
                                                        root.getTotalSpace(),
                                                        root.getFreeSpace(),
                                                        root.getUsableSpace())
                                        ))
                                .collect(Collectors.toList())
        };
    }

    protected boolean isConnectionSettingValid(ConnectionSetting connectionSetting) {
        return connectionSetting instanceof WindowsConnectionSetting;
    }
}
