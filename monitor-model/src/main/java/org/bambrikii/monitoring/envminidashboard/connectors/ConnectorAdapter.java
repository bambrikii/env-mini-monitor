package org.bambrikii.monitoring.envminidashboard.connectors;

public interface ConnectorAdapter {
    Object perform(ProbeAction<?, ?> action);
}
