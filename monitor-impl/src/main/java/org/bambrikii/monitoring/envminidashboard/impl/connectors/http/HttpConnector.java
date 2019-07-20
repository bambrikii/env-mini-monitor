package org.bambrikii.monitoring.envminidashboard.impl.connectors.http;

import org.bambrikii.monitoring.envminidashboard.connectors.AbstractConnector;
import org.bambrikii.monitoring.envminidashboard.connectors.ConnectorCommandable;
import org.bambrikii.monitoring.envminidashboard.result.MetricsResult;

import java.util.List;

public class HttpConnector extends AbstractConnector<HttpConnectionSetting> {
    @Override
    public List<MetricsResult> apply(HttpConnectionSetting connectionSetting, ConnectorCommandable... commands) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }
}
