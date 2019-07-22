package org.bambrikii.monitoring.envminidashboard.data.config;

import lombok.Getter;
import lombok.Setter;
import org.bambrikii.monitoring.envminidashboard.model.MetricsFamilible;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table
public class MetricsFamilyConfig implements MetricsFamilible {
    @Id
    @GeneratedValue(generator = "METRICS_FAMILY_CONFIG", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "METRICS_FAMILY_SEQ", sequenceName = "METRICS_FAMILY_SEQ")
    private Long id;
    @Column(nullable = false, unique = true)
    private String code;
}
