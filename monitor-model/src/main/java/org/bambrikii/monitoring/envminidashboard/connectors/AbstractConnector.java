package org.bambrikii.monitoring.envminidashboard.connectors;

import org.bambrikii.monitoring.envminidashboard.connectors.api.ConnectorAdapter;
import org.bambrikii.monitoring.envminidashboard.model.api.ConnConfiggable;

import java.util.logging.Logger;

public abstract class AbstractConnector<C extends ConnConfiggable> implements ConnectorAdapter, AutoCloseable {
    private static Logger log = Logger.getLogger(AbstractConnector.class.getName());

    private C config;

    public void init(C config) {
        this.config = config;
    }

    protected C getConfig() {
        return config;
    }

    public abstract void ensureOpen();

    public abstract void close();
}
