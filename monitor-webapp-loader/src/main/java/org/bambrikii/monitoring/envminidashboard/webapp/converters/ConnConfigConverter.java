package org.bambrikii.monitoring.envminidashboard.webapp.converters;

import org.bambrikii.monitoring.envminidashboard.data.config.ConnConfigEntity;
import org.bambrikii.monitoring.envminidashboard.data.config.HttpConnConfigEntity;
import org.bambrikii.monitoring.envminidashboard.data.config.WinConnConfigEntity;
import org.bambrikii.monitoring.envminidashboard.data.config.SshConnConfigEntity;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.http.HttpConnConfig;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.ssh.SshHostConnCfg;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.windows.WinConnConfig;
import org.bambrikii.monitoring.envminidashboard.model.ConnConfig;

public class ConnConfigConverter {
    public static ConnConfig convert(ConnConfigEntity entity) {
        if (entity instanceof SshConnConfigEntity) {
            return convert((SshConnConfigEntity) entity);
        }
        if (entity instanceof WinConnConfigEntity) {
            return convert((WinConnConfigEntity) entity);
        }
        if (entity instanceof HttpConnConfigEntity) {
            return convert((HttpConnConfigEntity) entity);
        }
        throw new IllegalArgumentException("Cannot convert class " + entity.getClass().getName());
    }

    private static HttpConnConfig convert(HttpConnConfigEntity entity) {
        HttpConnConfigEntity entity2 = entity;
        HttpConnConfig connConfig = new HttpConnConfig();
        connConfig.setHost(entity2.getHost());
        connConfig.setPort(entity2.getPort());
        return connConfig;
    }

    private static WinConnConfig convert(WinConnConfigEntity entity) {
        WinConnConfigEntity entity2 = entity;
        WinConnConfig connConfig = new WinConnConfig();
        connConfig.setHost(entity2.getHost());
        connConfig.setPort(entity2.getPort());
        return connConfig;
    }

    private static SshHostConnCfg convert(SshConnConfigEntity entity) {
        SshConnConfigEntity entity2 = entity;
        SshHostConnCfg connConfig = new SshHostConnCfg();
        connConfig.setHost(entity2.getHost());
        connConfig.setPort(entity2.getPort());
        connConfig.setUsername(entity2.getUsername());
        connConfig.setPassword(entity2.getPassword());
        return connConfig;
    }
}
