package org.bambrikii.monitoring.envminidashboard.web.converters;

import org.bambrikii.monitoring.envminidashboard.orm.model.PhysicalConnEntity;
import org.bambrikii.monitoring.envminidashboard.model.PhysicalConn;

import java.util.stream.Collectors;

public class PhysicalConnConverter {
    public static PhysicalConn convert(PhysicalConnEntity entity) {
        PhysicalConn conn = new PhysicalConn();
        conn.setTags(entity.getTags()
                .stream()
                .map(TagConverter::convert)
                .collect(Collectors.toList())
        );
        conn.setConfig(ConnConfigConverter.convert(entity.getConfig()));
        return conn;
    }

    public static PhysicalConnEntity convert(PhysicalConn conn) {
        PhysicalConnEntity entity = new PhysicalConnEntity();
        entity.setTags(conn.getTags()
                .stream()
                .map(TagConverter::convert)
                .collect(Collectors.toList())
        );
        entity.setConfig(ConnConfigConverter.convert(conn.getConfig()));
        return entity;
    }

    public static void convert(PhysicalConn pojo, PhysicalConnEntity entity) {
    }
}
