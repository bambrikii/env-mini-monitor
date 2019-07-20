package org.bambrikii.monitoring.envminidashboard.loaders;

import org.bambrikii.monitoring.envminidashboard.connectors.Connectable;
import org.bambrikii.monitoring.envminidashboard.connectors.ConnectionPool;
import org.bambrikii.monitoring.envminidashboard.model.ConnectionSetting;
import org.bambrikii.monitoring.envminidashboard.model.MetricsFamily;
import org.bambrikii.monitoring.envminidashboard.model.Tag;
import org.bambrikii.monitoring.envminidashboard.result.MetricsResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class MetricsFamilyLoader<S extends ConnectionSetting, C extends Connectable<S>> {
    private final List<MetricsFamily> metricsFamilies;

    public MetricsFamilyLoader(MetricsFamily... metricsFamilies) {
        this.metricsFamilies = Arrays.asList(metricsFamilies);
    }

    public List<MetricsFamily> getMetricsFamilies() {
        return Collections.unmodifiableList(metricsFamilies);
    }

    protected List<MetricsFamily> getMetricsFamilies0() {
        return metricsFamilies;
    }

    public final List<MetricsResult> load(ConnectionPool connectionPool, ConnectionSetting connectionSetting, Tag tag) {
        if (!isConnectionSettingValid(connectionSetting)) {
            throw new IllegalArgumentException("Invalid ConnectionSetting type " + connectionSetting + "!");
        }
        S connectionSettingsLocal = (S) connectionSetting;
        C connection = connectionPool.findConnection(connectionSetting);
        connection.connect(connectionSettingsLocal);
        return loadImpl(connection);
    }

    protected abstract boolean isConnectionSettingValid(ConnectionSetting connectionSetting);

    protected abstract List<MetricsResult> loadImpl(C connection);
}
