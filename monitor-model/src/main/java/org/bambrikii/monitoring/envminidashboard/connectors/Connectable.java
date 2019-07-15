package org.bambrikii.monitoring.envminidashboard.connectors;

import org.bambrikii.monitoring.envminidashboard.model.ConnectionSettings;

public interface Connectable<C extends ConnectionSettings> {
    void connect(C connection);
}
