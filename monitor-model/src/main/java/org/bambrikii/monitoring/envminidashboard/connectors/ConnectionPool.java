package org.bambrikii.monitoring.envminidashboard.connectors;

import org.bambrikii.monitoring.envminidashboard.model.ConnectionSetting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectionPool {
    private Map<ConnectionSetting, Connectable> pool = new HashMap<>();
    private Map<String, List<Connectable>> connectionsByType = new HashMap<>();

    public ConnectionPool register(ConnectionSetting connectionSetting, Connectable connector) {
        if (!pool.containsKey(connectionSetting)) {
            pool.put(connectionSetting, connector);
            String connectionSettingsTypeName = connectionSetting.getClass().getName();
            if (!connectionsByType.containsKey(connectionSettingsTypeName)) {
                connectionsByType.put(connectionSettingsTypeName, new ArrayList<>());
            }
            connectionsByType.get(connectionSettingsTypeName).add(connector);
        }
        pool.putIfAbsent(connectionSetting, connector);
        return this;
    }

    public <C extends Connectable> C findConnection(ConnectionSetting connectionSetting) {
        if (!pool.containsKey(connectionSetting)) {
            throw new IllegalArgumentException("No connectionSetting found for " + connectionSetting + "!");
        }
        return (C) pool.get(connectionSetting);
    }

    public List<Connectable> findConnections(Class<? extends ConnectionSetting> connectionSettingsType) {
        return connectionsByType.get(connectionSettingsType.getName());
    }
}
