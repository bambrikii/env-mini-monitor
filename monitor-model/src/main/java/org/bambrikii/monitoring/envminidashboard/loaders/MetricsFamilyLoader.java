package org.bambrikii.monitoring.envminidashboard.loaders;

import org.bambrikii.monitoring.envminidashboard.connectors.AbstractConnector;
import org.bambrikii.monitoring.envminidashboard.connectors.ConnectionPool;
import org.bambrikii.monitoring.envminidashboard.connectors.ConnectorCommandable;
import org.bambrikii.monitoring.envminidashboard.model.ConnectionSettingable;
import org.bambrikii.monitoring.envminidashboard.model.MetricsFamilible;
import org.bambrikii.monitoring.envminidashboard.model.Taggable;
import org.bambrikii.monitoring.envminidashboard.result.MetricsResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class MetricsFamilyLoader<S extends ConnectionSettingable, C extends AbstractConnector<S>> {
    private final List<MetricsFamilible> metricsFamilies;

    public MetricsFamilyLoader(MetricsFamilible... metricsFamilies) {
        this.metricsFamilies = Arrays.asList(metricsFamilies);
    }

    public List<MetricsFamilible> getMetricsFamilies() {
        return Collections.unmodifiableList(metricsFamilies);
    }

    public final List<MetricsResult> load(ConnectionPool connectionPool, S connectionSetting, Taggable tag) {
        if (!isConnectionSettingValid(connectionSetting)) {
            return Collections.emptyList();
        }
        C connection = connectionPool.findConnection(connectionSetting);

        return connection.apply(connectionSetting, getCommands());
    }

    protected abstract ConnectorCommandable[] getCommands();

    protected abstract boolean isConnectionSettingValid(ConnectionSettingable connectionSetting);
}
