package org.bambrikii.monitoring.envminidashboard.impl.connectors.windows;

import org.bambrikii.monitoring.envminidashboard.connectors.AbstractConnector;
import org.bambrikii.monitoring.envminidashboard.connectors.ProbeAction;
import org.bambrikii.monitoring.envminidashboard.connectors.ProbeActionPerformer;

import java.util.function.Supplier;

public class SmbConnector extends AbstractConnector<WinConnConfig> {
    @Override
    public void ensureOpen() {
        WinConnConfig config = getConfig();
        String hostName = config.getHost();
        int port = config.getPort();
        // TODO:
    }

    @Override
    public void close() {

    }

    @Override
    public Object perform(ProbeAction action) {
        return action.exec(new ProbeActionPerformer<Object, Object>() {
            @Override
            public Object cmd(Supplier<Object> perform) {
                return perform.get();
            }
        });
    }
}
