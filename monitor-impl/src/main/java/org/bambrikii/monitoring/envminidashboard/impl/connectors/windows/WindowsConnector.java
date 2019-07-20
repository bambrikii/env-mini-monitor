package org.bambrikii.monitoring.envminidashboard.impl.connectors.windows;

import org.bambrikii.monitoring.envminidashboard.connectors.AbstractConnector;
import org.bambrikii.monitoring.envminidashboard.connectors.ConnectorCommandable;
import org.bambrikii.monitoring.envminidashboard.result.MetricsResult;

import java.util.List;

public class WindowsConnector extends AbstractConnector<WindowsConnectionSetting> {
    @Override
    public List<MetricsResult> apply(WindowsConnectionSetting connectionSetting, ConnectorCommandable... commands) {
        String hostName = connectionSetting.getHost();
        int port = connectionSetting.getPort();
        return iterate(
                command -> command.execute(o -> o),
                commands
        );
    }
}
