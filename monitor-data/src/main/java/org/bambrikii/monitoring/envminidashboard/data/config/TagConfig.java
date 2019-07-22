package org.bambrikii.monitoring.envminidashboard.data.config;

import lombok.Getter;
import lombok.Setter;
import org.bambrikii.monitoring.envminidashboard.model.Taggable;

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

@Getter
@Setter
@Entity
@Table
public class TagConfig implements Taggable<MetricsFamilyConfig, ConnectionSettingConfig> {
    @Id
    @GeneratedValue(generator = "TAG_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "TAG_SEQ", sequenceName = "TAG_SEQ")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany
    private List<MetricsFamilyConfig> metricsFamilies = new ArrayList<>();

    @ManyToMany
    private List<ConnectionSettingConfig> connectionSettings = new ArrayList<>();
}
