package org.bambrikii.monitoring.envminidashboard.data.config;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class TagConfigEntity {
    @Id
    @GeneratedValue(generator = "TAG_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "TAG_SEQ", sequenceName = "TAG_SEQ")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany
    private List<MetricsFamilyConfigEntity> metricsNamespaces = new ArrayList<>();

    @ManyToMany
    private List<ConnConfigEntity> connConfigs = new ArrayList<>();

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

    public List<MetricsFamilyConfigEntity> getMetricsNamespaces() {
        return metricsNamespaces;
    }

    public void setMetricsNamespaces(List<MetricsFamilyConfigEntity> metricsNamespaces) {
        this.metricsNamespaces = metricsNamespaces;
    }

    public List<ConnConfigEntity> getConnConfigs() {
        return connConfigs;
    }

    public void setConnConfigs(List<ConnConfigEntity> connConfigs) {
        this.connConfigs = connConfigs;
    }
}
