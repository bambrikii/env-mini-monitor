package org.bambrikii.monitoring.envminidashboard.impl.dashboard;

import org.bambrikii.monitoring.envminidashboard.connectors.AbstractConnector;
import org.bambrikii.monitoring.envminidashboard.connectors.AbstractProbe;
import org.bambrikii.monitoring.envminidashboard.model.ConnConfig;
import org.bambrikii.monitoring.envminidashboard.model.Dashboard;
import org.bambrikii.monitoring.envminidashboard.model.Dashboardable;
import org.bambrikii.monitoring.envminidashboard.model.Env;
import org.bambrikii.monitoring.envminidashboard.model.Environmentable;
import org.bambrikii.monitoring.envminidashboard.model.MetricsNamespaceable;
import org.bambrikii.monitoring.envminidashboard.model.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashboardBuilder {
    private Env env;
    private final List<Environmentable> envs = new ArrayList<>();

    public DashboardBuilder env(String code) {
        env = new Env();
        env.setCode(code);
        envs.add(env);
        return this;
    }

    public DashboardBuilder tag(MetricsNamespaceable namespace, Tag... tags) {
        env.tag(namespace, tags);
        return this;
    }

    public DashboardBuilder tag(ConnConfig connConfig, Tag... tags) {
        env.tag(connConfig, tags);
        return this;
    }

    public Dashboardable buildModel() {
        Dashboard dashboard = new Dashboard();
        dashboard.setEnvs(envs);
        return dashboard;
    }


    private final List<AbstractProbe> probes = new ArrayList<>();
    private final Map<ConnConfig, AbstractConnector> connectors = new HashMap<>();

    public DashboardBuilder probe(AbstractProbe probe) {
        probes.add(probe);
        return this;
    }

    public DashboardBuilder connector(ConnConfig connConfig, AbstractConnector connector) {
        connectors.put(connConfig, connector);
        return this;
    }

    public DashboardLoader buildImpl() {
        DashboardLoader loader = new DashboardLoader();
        connectors.forEach((key, value) -> loader.connector(key, value));
        probes.forEach(probe -> loader.probe(probe));
        return loader;
    }
}
