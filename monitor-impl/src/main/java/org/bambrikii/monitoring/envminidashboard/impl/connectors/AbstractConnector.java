package org.bambrikii.monitoring.envminidashboard.impl.connectors;

import org.bambrikii.monitoring.envminidashboard.connectors.Connectable;
import org.bambrikii.monitoring.envminidashboard.model.ConnectionSettings;

public abstract class AbstractConnector<C extends ConnectionSettings> implements Connectable<C> {
    @Override
    public abstract void connect(C connection);
}
