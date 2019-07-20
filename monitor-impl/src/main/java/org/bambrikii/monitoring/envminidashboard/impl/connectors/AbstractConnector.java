package org.bambrikii.monitoring.envminidashboard.impl.connectors;

import org.bambrikii.monitoring.envminidashboard.connectors.Connectable;
import org.bambrikii.monitoring.envminidashboard.model.ConnectionSetting;

public abstract class AbstractConnector<C extends ConnectionSetting> implements Connectable<C> {
    @Override
    public abstract void connect(ConnectionSetting connectionSetting);
}
