package org.bambrikii.monitoring.envminidashboard.impl.loader;

import org.bambrikii.monitoring.envminidashboard.connectors.ConnectorCommandable;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.ssh.SshConnectionSetting;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.ssh.SshConnector;
import org.bambrikii.monitoring.envminidashboard.loaders.MetricsFamilyLoader;
import org.bambrikii.monitoring.envminidashboard.model.ConnectionSetting;
import org.bambrikii.monitoring.envminidashboard.result.Metric;
import org.bambrikii.monitoring.envminidashboard.result.MetricsResult;

import java.util.Arrays;

import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsFactory.SYS_METRICS_FAMILY;

public class LinuxSysMetricsLoader extends MetricsFamilyLoader<SshConnectionSetting, SshConnector> {
    public LinuxSysMetricsLoader() {
        super(SYS_METRICS_FAMILY);
    }

    @Override
    protected ConnectorCommandable[] getCommands() {
        return new ConnectorCommandable[]{
                command -> Arrays.asList(new MetricsResult(
                        new Metric(SYS_METRICS_FAMILY.getCode() + ".ls la"),
                        command.apply("mpstat -P ALL | grep all")
                ))
        };
    }

    @Override
    protected boolean isConnectionSettingValid(ConnectionSetting connectionSetting) {
        return connectionSetting instanceof SshConnectionSetting;
    }
}
