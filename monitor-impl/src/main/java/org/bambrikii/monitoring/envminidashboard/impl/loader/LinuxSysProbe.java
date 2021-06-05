package org.bambrikii.monitoring.envminidashboard.impl.loader;

import org.bambrikii.monitoring.envminidashboard.connectors.AbstractProbe;
import org.bambrikii.monitoring.envminidashboard.connectors.ProbeAction;
import org.bambrikii.monitoring.envminidashboard.connectors.ProbeActionPerformer;
import org.bambrikii.monitoring.envminidashboard.connectors.ProbeResultCollector;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.ssh.SshConnector;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.ssh.SshHostConnCfg;
import org.bambrikii.monitoring.envminidashboard.model.ConnConfig;

import java.util.function.Supplier;

import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsRegistry.SYS_CPU_USAGE_METRIC;
import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsRegistry.SYS_NAMESPACE;

public class LinuxSysProbe extends AbstractProbe<SshHostConnCfg, SshConnector> {
    public LinuxSysProbe() {
        super(SYS_NAMESPACE);
    }

    @Override
    protected void load(SshConnector connector, ProbeResultCollector collector) {
        collector.accept(
                SYS_CPU_USAGE_METRIC,
                connector.perform(new ProbeAction() {
                    @Override
                    public Object exec(ProbeActionPerformer cmd) {
                        return cmd.cmd(new Supplier() {
                            @Override
                            public String get() {
                                return "mpstat | grep \"all\" | awk '{print 100-$13}'";
                            }
                        });
                    }
                })
        );
        collector.accept(
                SYS_CPU_USAGE_METRIC,
                connector.perform(new ProbeAction<>() {
                    @Override
                    public Object exec(ProbeActionPerformer cmd) {
                        return cmd.cmd(new Supplier() {
                            @Override
                            public Object get() {
                                return "free | grep \"Mem:\" | awk '{print($3/$2*100)}'";
                            }
                        });
                    }
                })
        );
    }

    @Override
    protected boolean isSupported(ConnConfig connectionSetting) {
        return connectionSetting instanceof SshHostConnCfg;
    }
}
