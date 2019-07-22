package org.bambrikii.monitoring.envminidashboard.impl.connectors;

import org.bambrikii.monitoring.envminidashboard.impl.connectors.http.HttpConnectionSetting;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.ssh.SshConnectionSetting;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.windows.WindowsConnectionSetting;

public class ConnectionSettingsFactory {
    private ConnectionSettingsFactory() {
    }

    public SshConnectionSetting sshConnectionSetting() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public HttpConnectionSetting httpConnectionSetting() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public WindowsConnectionSetting windowsConnectionSetting() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
