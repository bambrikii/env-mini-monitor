package org.bambrikii.monitoring.envminidashboard.loaders;

import org.bambrikii.monitoring.envminidashboard.connectors.AbstractConnector;
import org.bambrikii.monitoring.envminidashboard.connectors.ConnectionPool;
import org.bambrikii.monitoring.envminidashboard.connectors.ConnectorCommandable;
import org.bambrikii.monitoring.envminidashboard.model.ConnectionSetting;
import org.bambrikii.monitoring.envminidashboard.model.MetricsFamily;
import org.bambrikii.monitoring.envminidashboard.model.Tag;
import org.bambrikii.monitoring.envminidashboard.result.MetricsResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class MetricsFamilyLoader<S extends ConnectionSetting, C extends AbstractConnector<S>> {
    private final List<MetricsFamily> metricsFamilies;

    public MetricsFamilyLoader(MetricsFamily... metricsFamilies) {
        this.metricsFamilies = Arrays.asList(metricsFamilies);
    }

    public List<MetricsFamily> getMetricsFamilies() {
        return Collections.unmodifiableList(metricsFamilies);
    }

    public final List<MetricsResult> load(ConnectionPool connectionPool, S connectionSetting, Tag tag) {
        if (!isConnectionSettingValid(connectionSetting)) {
            return Collections.emptyList();
        }
        C connection = connectionPool.findConnection(connectionSetting);

        return connection.apply(connectionSetting, getCommands());
    }

    protected abstract ConnectorCommandable[] getCommands();

    protected abstract boolean isConnectionSettingValid(ConnectionSetting connectionSetting);
}
