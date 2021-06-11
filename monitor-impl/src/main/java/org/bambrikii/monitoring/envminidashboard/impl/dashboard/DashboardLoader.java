package org.bambrikii.monitoring.envminidashboard.impl.dashboard;

import org.bambrikii.monitoring.envminidashboard.connectors.AbstractConnector;
import org.bambrikii.monitoring.envminidashboard.connectors.AbstractProbe;
import org.bambrikii.monitoring.envminidashboard.connectors.ConnectionPool;
import org.bambrikii.monitoring.envminidashboard.model.api.ConnConfiggable;
import org.bambrikii.monitoring.envminidashboard.model.api.Dashboardable;
import org.bambrikii.monitoring.envminidashboard.model.api.Environmentable;
import org.bambrikii.monitoring.envminidashboard.model.api.MetricLoggable;
import org.bambrikii.monitoring.envminidashboard.model.api.PhysicalConnectable;
import org.bambrikii.monitoring.envminidashboard.model.api.Taggable;

import java.util.ArrayList;
import java.util.List;

public class DashboardLoader<
        E extends Environmentable<P>,
        P extends PhysicalConnectable<T, C, M>,
        T extends Taggable,
        C extends ConnConfiggable,
        M extends MetricLoggable
        > {
    private final ConnectionPool connPool = new ConnectionPool();
    private final List<AbstractProbe> probes = new ArrayList<>();

    public DashboardLoader() {
    }

    public DashboardLoader connector(ConnConfiggable connConfig, AbstractConnector connector) {
        connPool.connector(connConfig, connector);
        return this;
    }

    public DashboardLoader probe(AbstractProbe probe) {
        probes.add(probe);
        return this;
    }

    public void load(Dashboardable<E> dashboard) {
        for (E env : dashboard.getEnvs()) {
            for (P conn : env.getConnections()) {
                C config = conn.getConfig();
                for (AbstractProbe probe : this.probes) {
                    probe.load(connPool, config, conn);
                }
            }
        }
    }
}
