package org.bambrikii.monitoring.envminidashboard.connectors;

public interface ProbeAction<T, R> {
    R exec(ProbeActionPerformer cmd);
}
