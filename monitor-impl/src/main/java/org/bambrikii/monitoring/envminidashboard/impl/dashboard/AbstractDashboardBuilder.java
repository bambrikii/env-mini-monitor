package org.bambrikii.monitoring.envminidashboard.impl.dashboard;

import org.bambrikii.monitoring.envminidashboard.connectors.AbstractConnector;
import org.bambrikii.monitoring.envminidashboard.connectors.AbstractProbe;
import org.bambrikii.monitoring.envminidashboard.model.api.ConnConfiggable;
import org.bambrikii.monitoring.envminidashboard.model.api.Dashboardable;
import org.bambrikii.monitoring.envminidashboard.model.api.Environmentable;
import org.bambrikii.monitoring.envminidashboard.model.api.MetricLoggable;
import org.bambrikii.monitoring.envminidashboard.model.api.MetricsNamespaceable;
import org.bambrikii.monitoring.envminidashboard.model.api.PhysicalConnectable;
import org.bambrikii.monitoring.envminidashboard.model.api.Taggable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractDashboardBuilder<
        E extends Environmentable<P>,
        P extends PhysicalConnectable<T, C, M>,
        T extends Taggable,
        C extends ConnConfiggable,
        M extends MetricLoggable,
        N extends MetricsNamespaceable
        > {
    private E env;
    private final List<E> envs = new ArrayList<>();

    public AbstractDashboardBuilder<E, P, T, C, M, N> env(String code) {
        env = newEnv();
        env.setCode(code);
        envs.add(env);
        return this;
    }

    protected abstract E newEnv();

    public AbstractDashboardBuilder<E, P, T, C, M, N> conn(C config, T tags) {
        P conn = newPhysicalConn();
        conn.setConfig(config);
        conn.setTags(Arrays.asList(tags));
        env.getConnections().add(conn);
        return this;
    }

    protected abstract P newPhysicalConn();

    public Dashboardable<E> buildModel() {
        Dashboardable<E> dashboard = newDashboard();
        dashboard.setEnvs(envs);
        return dashboard;
    }

    protected abstract Dashboardable<E> newDashboard();

    private final List<AbstractProbe> probes = new ArrayList<>();
    private final Map<ConnConfiggable, AbstractConnector> connectors = new HashMap<>();

    public AbstractDashboardBuilder<E, P, T, C, M, N> probe(AbstractProbe probe) {
        probes.add(probe);
        return this;
    }

    public AbstractDashboardBuilder<E, P, T, C, M, N> connector(ConnConfiggable connConfig, AbstractConnector connector) {
        connectors.put(connConfig, connector);
        return this;
    }

    public DashboardLoader<E, P, T, C, M> buildImpl() {
        DashboardLoader<E, P, T, C, M> loader = new DashboardLoader<>();
        connectors.forEach((key, value) -> loader.connector(key, value));
        probes.forEach(probe -> loader.probe(probe));
        return loader;
    }
}
