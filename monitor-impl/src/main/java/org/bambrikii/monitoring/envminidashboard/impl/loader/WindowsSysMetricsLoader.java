package org.bambrikii.monitoring.envminidashboard.impl.loader;

import org.bambrikii.monitoring.envminidashboard.impl.connectors.WindowsConnectionSetting;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.WindowsConnector;
import org.bambrikii.monitoring.envminidashboard.loaders.MetricsFamilyLoader;
import org.bambrikii.monitoring.envminidashboard.result.MetricsResult;
import org.bambrikii.monitoring.envminidashboard.model.ConnectionSetting;

import java.util.List;

import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsFactory.SYS_METRICS_FAMILY;

public class WindowsSysMetricsLoader extends MetricsFamilyLoader<WindowsConnectionSetting, WindowsConnector> {
    public WindowsSysMetricsLoader() {
        super(SYS_METRICS_FAMILY);
    }

    protected boolean isConnectionSettingValid(ConnectionSetting connectionSetting) {
        return connectionSetting instanceof WindowsConnectionSetting;
    }

    @Override
    protected List<MetricsResult> loadImpl(WindowsConnector connection) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
