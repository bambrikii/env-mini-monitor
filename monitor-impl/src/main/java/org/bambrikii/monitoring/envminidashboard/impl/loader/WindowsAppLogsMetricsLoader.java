package org.bambrikii.monitoring.envminidashboard.impl.loader;

import org.bambrikii.monitoring.envminidashboard.connectors.ConnectorCommandable;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.windows.WindowsConnectionSetting;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.windows.WindowsConnector;
import org.bambrikii.monitoring.envminidashboard.loaders.MetricsFamilyLoader;
import org.bambrikii.monitoring.envminidashboard.model.ConnectionSetting;

public class WindowsAppLogsMetricsLoader extends MetricsFamilyLoader<WindowsConnectionSetting, WindowsConnector> {
    @Override
    protected ConnectorCommandable[] getCommands() {
        return new ConnectorCommandable[0];
    }

    @Override
    protected boolean isConnectionSettingValid(ConnectionSetting connectionSetting) {
        return connectionSetting instanceof WindowsConnectionSetting;
    }
}
