package org.bambrikii.monitoring.envminidashboard.impl.loader;

import org.bambrikii.monitoring.envminidashboard.connectors.AbstractProbe;
import org.bambrikii.monitoring.envminidashboard.connectors.api.ProbeAction;
import org.bambrikii.monitoring.envminidashboard.connectors.api.ProbeResultCollector;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.ssh.SshConnector;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.ssh.SshHostConnConfigCfg;
import org.bambrikii.monitoring.envminidashboard.model.api.ConnConfiggable;

import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsRegistry.SYS_CPU_USAGE_METRIC;
import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsRegistry.SYS_NAMESPACE;

public class LinuxSysProbe extends AbstractProbe<SshHostConnConfigCfg, SshConnector> {
    public static final ProbeAction MPSTAT_ACTION = cmd -> cmd.cmd(() -> "mpstat | grep \"all\" | awk '{print 100-$13}'");
    private static final ProbeAction<Object, Object> MEM_ACTION = cmd -> cmd.cmd(() -> "free | grep \"Mem:\" | awk '{print($3/$2*100)}'");

    public LinuxSysProbe() {
        super(SYS_NAMESPACE);
    }

    @Override
    protected void load(SshConnector connector, ProbeResultCollector collector) {
        collector.accept(SYS_CPU_USAGE_METRIC, connector.perform(MPSTAT_ACTION), getTime());
        collector.accept(SYS_CPU_USAGE_METRIC, connector.perform(MEM_ACTION), getTime());
    }

    @Override
    protected boolean isSupported(ConnConfiggable connectionSetting) {
        return connectionSetting instanceof SshHostConnConfigCfg;
    }
}
