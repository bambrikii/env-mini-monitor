package org.bambrikii.monitoring.envminidashboard.impl.loader;

import org.bambrikii.monitoring.envminidashboard.connectors.ConnectorCommandable;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.ssh.SshConnectionSetting;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.ssh.SshConnector;
import org.bambrikii.monitoring.envminidashboard.loaders.MetricsFamilyLoader;
import org.bambrikii.monitoring.envminidashboard.model.ConnectionSettingable;
import org.bambrikii.monitoring.envminidashboard.result.Metric;
import org.bambrikii.monitoring.envminidashboard.result.MetricsResult;

import java.util.Arrays;

import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsFactory.SYS_CPU_USAGE_METRIC;
import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsFactory.SYS_MEM_USAGE_METRIC;
import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsFactory.SYS_METRICS_FAMILY;

public class LinuxSysMetricsLoader extends MetricsFamilyLoader<SshConnectionSetting, SshConnector> {
    public LinuxSysMetricsLoader() {
        super(SYS_METRICS_FAMILY);
    }

    @Override
    protected ConnectorCommandable[] getCommands() {
        return new ConnectorCommandable[]{
                command -> Arrays.asList(
                        new MetricsResult(
                                new Metric(SYS_CPU_USAGE_METRIC.getCode()),
                                command.apply("mpstat | grep \"all\" | awk '{print 100-$13}'")
                        ),
                        new MetricsResult(
                                new Metric(SYS_MEM_USAGE_METRIC.getCode()),
                                command.apply("free | grep \"Mem:\" | awk '{print($3/$2*100)}'")
                        ))
        };
    }

    @Override
    protected boolean isConnectionSettingValid(ConnectionSettingable connectionSetting) {
        return connectionSetting instanceof SshConnectionSetting;
    }
}
