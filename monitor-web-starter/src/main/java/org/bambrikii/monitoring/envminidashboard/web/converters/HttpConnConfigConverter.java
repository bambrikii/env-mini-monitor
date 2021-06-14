package org.bambrikii.monitoring.envminidashboard.web.converters;

import org.bambrikii.monitoring.envminidashboard.impl.connectors.http.HttpConnConfig;
import org.bambrikii.monitoring.envminidashboard.orm.model.HttpConnConfigEntity;

public class HttpConnConfigConverter {
    public static HttpConnConfig convert(HttpConnConfigEntity entity) {
        HttpConnConfig connConfig = new HttpConnConfig();
        convert(entity, connConfig);
        return connConfig;
    }

    public static HttpConnConfigEntity convert(HttpConnConfig pojo) {
        HttpConnConfigEntity entity = new HttpConnConfigEntity();
        convert(pojo, entity);
        return entity;
    }

    public static void convert(HttpConnConfigEntity entity, HttpConnConfig pojo) {
        pojo.setHost(entity.getHost());
        pojo.setPort(entity.getPort());
    }

    public static void convert(HttpConnConfig pojo, HttpConnConfigEntity entity) {
        entity.setHost(pojo.getHost());
        entity.setPort(pojo.getPort());
    }
}
