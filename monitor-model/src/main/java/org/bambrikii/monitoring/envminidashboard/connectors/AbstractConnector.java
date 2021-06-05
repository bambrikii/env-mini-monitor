package org.bambrikii.monitoring.envminidashboard.connectors;

import lombok.extern.java.Log;
import org.bambrikii.monitoring.envminidashboard.model.ConnConfig;

import java.util.logging.Logger;

@Log
public abstract class AbstractConnector<C extends ConnConfig> implements ConnectorAdapter, AutoCloseable {
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
