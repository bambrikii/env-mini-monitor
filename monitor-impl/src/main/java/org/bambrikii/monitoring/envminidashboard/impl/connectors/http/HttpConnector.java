package org.bambrikii.monitoring.envminidashboard.impl.connectors.http;

import org.bambrikii.monitoring.envminidashboard.connectors.AbstractConnector;
import org.bambrikii.monitoring.envminidashboard.connectors.ProbeAction;

public class HttpConnector extends AbstractConnector<HttpConnConfig> {
    @Override
    public void ensureOpen() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Object perform(ProbeAction<?, ?> action) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }
}
