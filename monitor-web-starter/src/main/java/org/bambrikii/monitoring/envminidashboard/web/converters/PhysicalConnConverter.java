package org.bambrikii.monitoring.envminidashboard.web.converters;

import org.bambrikii.monitoring.envminidashboard.model.ConnConfig;
import org.bambrikii.monitoring.envminidashboard.model.MetricLog;
import org.bambrikii.monitoring.envminidashboard.model.Tag;
import org.bambrikii.monitoring.envminidashboard.orm.model.ConnConfigEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.MetricLogEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.PhysicalConnEntity;
import org.bambrikii.monitoring.envminidashboard.model.PhysicalConn;
import org.bambrikii.monitoring.envminidashboard.orm.model.TagEntity;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PhysicalConnConverter {
    public static PhysicalConn convert(PhysicalConnEntity entity) {
        PhysicalConn conn = new PhysicalConn();
        conn.setId(entity.getId());
        conn.setTags(entity.getTags()
                .stream()
                .map(TagConverter::convert)
                .collect(Collectors.toList())
        );
        ConnConfigEntity config = entity.getConfig();
        if (config != null) {
            conn.setConfig(ConnConfigConverter.convert(config));
        }
        return conn;
    }

    public static PhysicalConnEntity convert(PhysicalConn conn) {
        PhysicalConnEntity entity = new PhysicalConnEntity();
        entity.setTags(conn.getTags()
                .stream()
                .map(TagConverter::convert)
                .collect(Collectors.toList())
        );
        ConnConfig config = conn.getConfig();
        if (config != null) {
            entity.setConfig(ConnConfigConverter.convert(config));
        }
        return entity;
    }

    public static void convert(PhysicalConn pojo, PhysicalConnEntity entity,
                               Function<ConnConfig, ConnConfigEntity> connConfigMapper,
                               Function<Tag, TagEntity> tagMapper,
                               Function<MetricLog, MetricLogEntity> metricLogMapper
    ) {
        ConnConfig config = pojo.getConfig();
        if (config != null && connConfigMapper != null) {
            entity.setConfig(connConfigMapper.apply(config));
        }
        List<Tag> tags = pojo.getTags();
        if (tags != null && !tags.isEmpty() && tagMapper != null) {
            entity.setTags(tags
                    .stream()
                    .map(tagMapper)
                    .collect(Collectors.toList())
            );
        }
        List<MetricLog> metricLogs = pojo.getMetricLogs();
        if (metricLogs != null && !metricLogs.isEmpty() && metricLogMapper != null) {
            entity.setMetricLogs(metricLogs
                    .stream()
                    .map(metricLogMapper)
                    .collect(Collectors.toList())
            );
        }
    }
}
