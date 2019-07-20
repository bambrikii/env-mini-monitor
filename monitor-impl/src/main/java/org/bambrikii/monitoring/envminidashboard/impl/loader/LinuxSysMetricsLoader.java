package org.bambrikii.monitoring.envminidashboard.impl.loader;

import org.bambrikii.monitoring.envminidashboard.connectors.Connectable;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.SshConnectionSetting;
import org.bambrikii.monitoring.envminidashboard.loaders.MetricsFamilyLoader;
import org.bambrikii.monitoring.envminidashboard.result.MetricsResult;
import org.bambrikii.monitoring.envminidashboard.model.ConnectionSetting;

import java.util.List;

import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsFactory.SYS_METRICS_FAMILY;

public class LinuxSysMetricsLoader extends MetricsFamilyLoader {
    public LinuxSysMetricsLoader() {
        super(SYS_METRICS_FAMILY);
    }

    @Override
    protected boolean isConnectionSettingValid(ConnectionSetting connectionSetting) {
        return connectionSetting instanceof SshConnectionSetting;
    }

    @Override
    protected List<MetricsResult> loadImpl(Connectable connection) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
