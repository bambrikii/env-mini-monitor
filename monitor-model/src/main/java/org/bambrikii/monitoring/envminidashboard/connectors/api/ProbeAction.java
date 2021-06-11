package org.bambrikii.monitoring.envminidashboard.connectors.api;

public interface ProbeAction<T, R> {
    R exec(ProbeActionPerformer cmd);
}
