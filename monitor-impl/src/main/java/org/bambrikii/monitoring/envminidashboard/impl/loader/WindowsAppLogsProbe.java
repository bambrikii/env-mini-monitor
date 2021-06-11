package org.bambrikii.monitoring.envminidashboard.impl.loader;

import org.bambrikii.monitoring.envminidashboard.connectors.AbstractProbe;
import org.bambrikii.monitoring.envminidashboard.connectors.api.ProbeAction;
import org.bambrikii.monitoring.envminidashboard.connectors.api.ProbeResultCollector;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.windows.SmbConnector;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.windows.WinConnConfig;
import org.bambrikii.monitoring.envminidashboard.model.api.ConnConfiggable;

import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsRegistry.APP_LOGS_METRIC;

public class WindowsAppLogsProbe extends AbstractProbe<WinConnConfig, SmbConnector> {
    public static final ProbeAction LOGS_ACTION = cmd -> cmd.cmd(() -> "---");

    @Override
    protected void load(SmbConnector conn, ProbeResultCollector collector) {
        collector.accept(APP_LOGS_METRIC, conn.perform(LOGS_ACTION), getTime());
    }

    @Override
    protected boolean isSupported(ConnConfiggable connectionSetting) {
        return connectionSetting instanceof WinConnConfig;
    }
}
