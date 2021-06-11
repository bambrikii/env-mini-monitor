package org.bambrikii.monitoring.envminidashboard.connectors.api;

public interface ConnectorAdapter {
    Object perform(ProbeAction<?, ?> action);
}
