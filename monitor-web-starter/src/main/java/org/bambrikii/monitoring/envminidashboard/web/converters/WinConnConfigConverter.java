package org.bambrikii.monitoring.envminidashboard.web.converters;

import org.bambrikii.monitoring.envminidashboard.impl.connectors.windows.WinConnConfig;
import org.bambrikii.monitoring.envminidashboard.orm.model.WinConnConfigEntity;

public class WinConnConfigConverter {
    public static WinConnConfig convert(WinConnConfigEntity entity) {
        WinConnConfig connConfig = new WinConnConfig();
        convert(entity, connConfig);
        return connConfig;
    }

    public static WinConnConfigEntity convert(WinConnConfig pojo) {
        WinConnConfigEntity entity = new WinConnConfigEntity();
        convert(pojo, entity);
        return entity;
    }

    public static void convert(WinConnConfigEntity entity, WinConnConfig pojo) {
        pojo.setHost(entity.getHost());
        pojo.setPort(entity.getPort());
    }

    public static void convert(WinConnConfig pojo, WinConnConfigEntity entity) {
        entity.setHost(pojo.getHost());
        entity.setPort(pojo.getPort());
    }
}
