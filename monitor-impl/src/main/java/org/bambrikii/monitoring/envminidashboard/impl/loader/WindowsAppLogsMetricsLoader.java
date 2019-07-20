package org.bambrikii.monitoring.envminidashboard.impl.loader;

import org.bambrikii.monitoring.envminidashboard.connectors.Connectable;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.WindowsConnectionSetting;
import org.bambrikii.monitoring.envminidashboard.loaders.MetricsFamilyLoader;
import org.bambrikii.monitoring.envminidashboard.model.ConnectionSetting;
import org.bambrikii.monitoring.envminidashboard.result.MetricsResult;

import java.util.List;

public class WindowsAppLogsMetricsLoader extends MetricsFamilyLoader {
    @Override
    protected boolean isConnectionSettingValid(ConnectionSetting connectionSetting) {
        return connectionSetting instanceof WindowsConnectionSetting;
    }

    @Override
    protected List<MetricsResult> loadImpl(Connectable connection) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
