package org.bambrikii.monitoring.envminidashboard.impl.connectors;

import org.bambrikii.monitoring.envminidashboard.connectors.Connectable;
import org.bambrikii.monitoring.envminidashboard.model.ConnectionSetting;

public class WindowsConnector implements Connectable<WindowsConnectionSetting> {
    @Override
    public void connect(ConnectionSetting connectionSetting) {
        String hostName = connectionSetting.getHost();
    }
}
