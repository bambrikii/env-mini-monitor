package org.bambrikii.monitoring.envminidashboard.orm.model;

import lombok.ToString;
import org.bambrikii.monitoring.envminidashboard.model.Metric;
import org.bambrikii.monitoring.envminidashboard.model.api.PhysicalConnectable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ToString
@Entity
@Table
public class PhysicalConnEntity implements PhysicalConnectable<
        TagEntity,
        ConnConfigEntity,
        MetricLogEntity
        > {
    @Id
    @GeneratedValue(generator = "PHYSICAL_CONN_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "PHYSICAL_CONN_SEQ", sequenceName = "PHYSICAL_CONN_SEQ")
    private Long id;

    @ManyToOne
    private ConnConfigEntity config;
    @ManyToMany
    private List<TagEntity> tags = new ArrayList<>();
    @ManyToMany
    private Map<String, TagEntity> tagMap = new HashMap<>();
    @ManyToMany
    private List<MetricLogEntity> metricLogs = new ArrayList<>();

    private TagEntity ensureTag(TagEntity tag) {
        String tagName = tag.getName();
        if (tagMap.containsKey(tagName)) {
            return tagMap.get(tagName);
        }
        tagMap.put(tagName, tag);
        tags.add(tag);
        return tag;
    }

    @Override
    public List<TagEntity> getTags() {
        return tags;
    }

    @Override
    public void setTags(List<TagEntity> tags) {
        this.tags = tags;
    }

    @Override
    public ConnConfigEntity getConfig() {
        return config;
    }

    @Override
    public void setConfig(ConnConfigEntity config) {
        this.config = config;
    }

    @Override
    public List<MetricLogEntity> getMetricLogs() {
        return metricLogs;
    }

    @Override
    public void setMetricLogs(List<MetricLogEntity> metricLogs) {
        this.metricLogs = metricLogs;
    }

    @Override
    public void accept(Metric metric, Object value, Calendar time) {
        MetricLogEntity logEntity = new MetricLogEntity();
        logEntity.setCode(metric.getCode());
        logEntity.setValue(String.valueOf(value));
        logEntity.setTime(time);
        metricLogs.add(logEntity);
    }

    @Override
    public void fail(Metric metric, String message, Calendar time) {
        MetricLogEntity logEntity = new MetricLogEntity();
        logEntity.setCode(metric.getCode());
        logEntity.setValue(message);
        logEntity.setTime(time);
        metricLogs.add(logEntity);
    }
}
