package org.bambrikii.monitoring.envminidashboard.data.config;

import lombok.Getter;
import lombok.Setter;
import org.bambrikii.monitoring.envminidashboard.model.MetricsNamespaceable;

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
public class MetricsFamilyConfigEntity implements MetricsNamespaceable {
    @Id
    @GeneratedValue(generator = "METRICS_FAMILY_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "METRICS_FAMILY_SEQ", sequenceName = "METRICS_FAMILY_SEQ")
    private Long id;
    @Column(nullable = false, unique = true)
    private String code;
}
