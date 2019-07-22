package org.bambrikii.monitoring.envminidashboard.data.statistics;

import lombok.Getter;
import lombok.Setter;
import org.bambrikii.monitoring.envminidashboard.data.config.ConnectionSettingConfig;
import org.bambrikii.monitoring.envminidashboard.data.config.TagConfig;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table
public class MetricStatistics {
    @Id
    @GeneratedValue(generator = "METRICS_STATS", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "METRICS_STATS_SEQ", sequenceName = "METRICS_STATS_SEQ")
    private Long id;

    @ManyToOne
    private ConnectionSettingConfig connectionSetting;

    @ManyToOne
    private TagConfig tag;

    @Column(nullable = false)
    private String code;

    @Column
    private Object value;
}
