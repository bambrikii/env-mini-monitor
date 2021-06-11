package org.bambrikii.monitoring.envminidashboard.data.config;

import org.bambrikii.monitoring.envminidashboard.data.statistics.MetricLogEntity;
import org.bambrikii.monitoring.envminidashboard.model.api.Taggable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class TagEntity implements Taggable {
    @Id
    @GeneratedValue(generator = "TAG_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "TAG_SEQ", sequenceName = "TAG_SEQ")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<ConnConfigEntity> connConfigs = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<MetricLogEntity> statistics = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ConnConfigEntity> getConnConfigs() {
        return connConfigs;
    }

    public void setConnConfigs(List<ConnConfigEntity> connConfigs) {
        this.connConfigs = connConfigs;
    }

    public List<MetricLogEntity> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<MetricLogEntity> statistics) {
        this.statistics = statistics;
    }
}
