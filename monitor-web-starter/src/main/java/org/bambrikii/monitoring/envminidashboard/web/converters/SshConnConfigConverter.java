package org.bambrikii.monitoring.envminidashboard.web.converters;

import org.bambrikii.monitoring.envminidashboard.impl.connectors.ssh.SshConnConfig;
import org.bambrikii.monitoring.envminidashboard.orm.model.SshConnConfigEntity;

public class SshConnConfigConverter {
    public static SshConnConfig convert(SshConnConfigEntity entity) {
        SshConnConfig pojo = new SshConnConfig();
        convert(entity, pojo);
        return pojo;
    }

    public static SshConnConfigEntity convert(SshConnConfig pojo) {
        SshConnConfigEntity entity = new SshConnConfigEntity();
        convert(pojo, entity);
        return entity;
    }

    public static void convert(SshConnConfigEntity entity, SshConnConfig pojo) {
        pojo.setId(entity.getId());
        pojo.setHost(entity.getHost());
        pojo.setPort(entity.getPort());
        pojo.setUsername(entity.getUsername());
        pojo.setPassword(entity.getPassword());
    }

    public static void convert(SshConnConfig pojo, SshConnConfigEntity entity) {
        entity.setHost(pojo.getHost());
        entity.setPort(pojo.getPort());
        entity.setUsername(pojo.getUsername());
        entity.setPassword(pojo.getPassword());
    }
}
