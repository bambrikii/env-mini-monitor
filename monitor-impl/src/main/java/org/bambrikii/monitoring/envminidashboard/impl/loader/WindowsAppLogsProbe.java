package org.bambrikii.monitoring.envminidashboard.impl.loader;

import org.bambrikii.monitoring.envminidashboard.connectors.AbstractProbe;
import org.bambrikii.monitoring.envminidashboard.connectors.ProbeAction;
import org.bambrikii.monitoring.envminidashboard.connectors.ProbeActionPerformer;
import org.bambrikii.monitoring.envminidashboard.connectors.ProbeResultCollector;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.windows.SmbConnector;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.windows.WinConnConfig;
import org.bambrikii.monitoring.envminidashboard.model.ConnConfig;

import java.util.function.Supplier;

import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsRegistry.APP_LOGS_METRIC;

public class WindowsAppLogsProbe extends AbstractProbe<WinConnConfig, SmbConnector> {
    @Override
    protected void load(SmbConnector conn, ProbeResultCollector collector) {
//        throw new UnsupportedOperationException("Not yet implemented!");
        collector.accept(
                APP_LOGS_METRIC,
                conn.perform(new ProbeAction() {
                    @Override
                    public Object exec(ProbeActionPerformer cmd) {
                        return cmd.cmd(new Supplier() {
                            @Override
                            public Object get() {
                                return "---";
                            }
                        });
                    }
                })
        );

    }

    @Override
    protected boolean isSupported(ConnConfig connectionSetting) {
        return connectionSetting instanceof WinConnConfig;
    }
}
