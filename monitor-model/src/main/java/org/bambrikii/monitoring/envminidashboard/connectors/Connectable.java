package org.bambrikii.monitoring.envminidashboard.connectors;

import org.bambrikii.monitoring.envminidashboard.model.ConnectionSetting;

public interface Connectable<C extends ConnectionSetting> {
    void connect(ConnectionSetting connectionSetting);
}
