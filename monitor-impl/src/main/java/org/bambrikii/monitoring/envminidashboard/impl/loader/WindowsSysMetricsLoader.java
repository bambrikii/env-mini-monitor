package org.bambrikii.monitoring.envminidashboard.impl.loader;

import org.bambrikii.monitoring.envminidashboard.impl.connectors.WindowsConnectionSetting;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.WindowsConnector;
import org.bambrikii.monitoring.envminidashboard.impl.metrics.FileSystemMetricsValue;
import org.bambrikii.monitoring.envminidashboard.loaders.MetricsFamilyLoader;
import org.bambrikii.monitoring.envminidashboard.model.ConnectionSetting;
import org.bambrikii.monitoring.envminidashboard.result.Metric;
import org.bambrikii.monitoring.envminidashboard.result.MetricsResult;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsFactory.*;

public class WindowsSysMetricsLoader extends MetricsFamilyLoader<WindowsConnectionSetting, WindowsConnector> {
    public WindowsSysMetricsLoader() {
        super(SYS_METRICS_FAMILY);
    }

    protected boolean isConnectionSettingValid(ConnectionSetting connectionSetting) {
        return connectionSetting instanceof WindowsConnectionSetting;
    }

    @Override
    protected List<MetricsResult> loadImpl(WindowsConnector connection) {
        List<MetricsResult> results = new ArrayList<>();
        results.add(new MetricsResult(SYS_CPU_USAGE_METRIC, Runtime.getRuntime().availableProcessors()));
        results.add(new MetricsResult(SYS_MEM_USAGE_METRIC, Runtime.getRuntime().freeMemory()));
        Arrays.asList(File.listRoots())
                .forEach(root ->
                        results.add(new MetricsResult(
                                new Metric(SYS_FILESYSTEM_USAGE_METRIC.getCode() + "." + root.getName()),
                                new FileSystemMetricsValue(root.getAbsolutePath(),
                                        root.getTotalSpace(),
                                        root.getFreeSpace(),
                                        root.getUsableSpace())
                        )));
        return results;
    }
}
