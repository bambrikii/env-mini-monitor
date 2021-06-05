package org.bambrikii.monitoring.envminidashboard.impl.dashboard;

import org.bambrikii.monitoring.envminidashboard.connectors.AbstractConnector;
import org.bambrikii.monitoring.envminidashboard.connectors.AbstractProbe;
import org.bambrikii.monitoring.envminidashboard.connectors.ConnectionPool;
import org.bambrikii.monitoring.envminidashboard.model.ConnConfig;
import org.bambrikii.monitoring.envminidashboard.model.Dashboardable;
import org.bambrikii.monitoring.envminidashboard.model.Environmentable;
import org.bambrikii.monitoring.envminidashboard.model.Taggable;
import org.bambrikii.monitoring.envminidashboard.result.DashboardResult;

import java.util.ArrayList;
import java.util.List;

public class DashboardLoader {
    private final ConnectionPool connPool = new ConnectionPool();
    private final List<AbstractProbe> probes = new ArrayList<>();
    private final DashboardResult collector = new DashboardResult();

    public DashboardLoader() {
    }

    public DashboardLoader connector(ConnConfig connConfig, AbstractConnector connector) {
        connPool.connector(connConfig, connector);
        return this;
    }

    public DashboardLoader probe(AbstractProbe probe) {
        probes.add(probe);
        return this;
    }

    public DashboardResult load(Dashboardable dashboard) {
        for (Environmentable env : (List<Environmentable>) dashboard.getEnvs()) {
            collector.env(env.getCode());
            for (Taggable tag : (List<Taggable>) env.getTags()) {
                collector.tag(tag.getName());
                for (ConnConfig config : (List<ConnConfig>) tag.getConnConfigs()) {
                    for (AbstractProbe probe : this.probes) {
                        probe.load(connPool, config, collector);
                    }
                }
            }
        }
        return collector;
    }
}
