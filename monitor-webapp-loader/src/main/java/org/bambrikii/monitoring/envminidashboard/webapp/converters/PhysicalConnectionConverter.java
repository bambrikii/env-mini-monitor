package org.bambrikii.monitoring.envminidashboard.webapp.converters;

import org.bambrikii.monitoring.envminidashboard.data.config.PhysicalConnEntity;
import org.bambrikii.monitoring.envminidashboard.model.PhysicalConn;

import java.util.stream.Collectors;

public class PhysicalConnectionConverter {
    public static PhysicalConn convert(PhysicalConnEntity entity) {
        PhysicalConn connection = new PhysicalConn();
        connection.setTags(entity.getTags()
                .stream()
                .map(TagConverter::convert)
                .collect(Collectors.toList())
        );
        connection.setConfig(ConnConfigConverter.convert(entity.getConfig()));
        return connection;
    }
}
