package org.bambrikii.monitoring.envminidashboard.impl.connectors;

import org.bambrikii.monitoring.envminidashboard.impl.connectors.http.HttpConnConfig;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.ssh.SshConnConfig;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.windows.WinConnConfig;

public class ConnectionSettingsFactory {
    private ConnectionSettingsFactory() {
    }

    public SshConnConfig sshConnectionSetting() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public HttpConnConfig httpConnectionSetting() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public WinConnConfig windowsConnectionSetting() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
