package org.bambrikii.monitoring.envminidashboard.connectors;

import org.bambrikii.monitoring.envminidashboard.model.api.ConnConfiggable;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ConnectionPool {
    private static Logger log = Logger.getLogger(ConnectionPool.class.getName());

    private Map<ConnConfiggable, AbstractConnector> connPool = new HashMap<>();

    public ConnectionPool connector(ConnConfiggable connConfig, AbstractConnector connector) {
        if (connPool.containsKey(connConfig)) {
            log.warning("Configuration already exists for " + connConfig);
            return this;
        }

        ensureConn(connConfig, connector);

        return this;
    }

    private void ensureConn(ConnConfiggable config, AbstractConnector conn) {
        conn.init(config);
        if (!connPool.containsKey(config)) {
            connPool.put(config, conn);
        }
    }

    public <C extends AbstractConnector> C findOne(ConnConfiggable config) {
        if (!connPool.containsKey(config)) {
            throw new IllegalArgumentException("No connectionSetting found for " + config + "!");
        }

        AbstractConnector conn = connPool.get(config);
        conn.ensureOpen();

        return (C) conn;
    }

    public void close(ConnConfiggable config) {
        AbstractConnector conn = connPool.get(config);
        conn.close();
    }
}
