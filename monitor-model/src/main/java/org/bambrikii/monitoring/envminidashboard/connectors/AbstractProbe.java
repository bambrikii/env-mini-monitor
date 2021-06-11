package org.bambrikii.monitoring.envminidashboard.connectors;

import org.bambrikii.monitoring.envminidashboard.connectors.api.ProbeResultCollector;
import org.bambrikii.monitoring.envminidashboard.model.api.ConnConfiggable;
import org.bambrikii.monitoring.envminidashboard.model.api.MetricsNamespaceable;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public abstract class AbstractProbe<S extends ConnConfiggable, C extends AbstractConnector<S>> {
    private final List<MetricsNamespaceable> namespaces;

    public AbstractProbe(MetricsNamespaceable... namespaces) {
        this.namespaces = Collections.unmodifiableList(Arrays.asList(namespaces));
    }

    public List<MetricsNamespaceable> getNamespaces() {
        return namespaces;
    }

    public final void load(ConnectionPool connPool, S connConfig, ProbeResultCollector collector) {
        if (!isSupported(connConfig)) {
            return;
        }
        C conn = connPool.findOne(connConfig);

        conn.ensureOpen();

        load(conn, collector);
    }

    protected Calendar getTime() {
        return Calendar.getInstance();
    }

    protected abstract void load(C conn, ProbeResultCollector collector);

    protected abstract boolean isSupported(ConnConfiggable connectionSetting);
}
