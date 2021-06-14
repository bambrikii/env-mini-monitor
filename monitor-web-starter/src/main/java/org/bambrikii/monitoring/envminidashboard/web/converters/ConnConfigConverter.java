package org.bambrikii.monitoring.envminidashboard.web.converters;

import org.bambrikii.monitoring.envminidashboard.impl.connectors.http.HttpConnConfig;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.ssh.SshConnConfig;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.windows.WinConnConfig;
import org.bambrikii.monitoring.envminidashboard.model.ConnConfig;
import org.bambrikii.monitoring.envminidashboard.orm.model.ConnConfigEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.HttpConnConfigEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.SshConnConfigEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.WinConnConfigEntity;

public class ConnConfigConverter {
    public static ConnConfig convert(ConnConfigEntity entity) {
        if (entity instanceof SshConnConfigEntity) {
            return SshConnConfigConverter.convert((SshConnConfigEntity) entity);
        }
        if (entity instanceof WinConnConfigEntity) {
            return WinConnConfigConverter.convert((WinConnConfigEntity) entity);
        }
        if (entity instanceof HttpConnConfigEntity) {
            return HttpConnConfigConverter.convert((HttpConnConfigEntity) entity);
        }
        throw new IllegalArgumentException("Cannot find converter for type " + entity.getClass().getName());
    }

    public static ConnConfigEntity convert(ConnConfig pojo) {
        if (pojo instanceof SshConnConfig) {
            return SshConnConfigConverter.convert((SshConnConfig) pojo);
        }
        if (pojo instanceof WinConnConfig) {
            return WinConnConfigConverter.convert((WinConnConfig) pojo);
        }
        if (pojo instanceof HttpConnConfig) {
            return HttpConnConfigConverter.convert((HttpConnConfig) pojo);
        }
        throw new IllegalArgumentException("Cannot find converter for type " + pojo.getClass().getName());
    }
}
