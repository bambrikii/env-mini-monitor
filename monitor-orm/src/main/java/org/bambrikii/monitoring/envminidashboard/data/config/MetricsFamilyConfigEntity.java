package org.bambrikii.monitoring.envminidashboard.data.config;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class MetricsFamilyConfigEntity {
    @Id
    @GeneratedValue(generator = "METRICS_FAMILY_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "METRICS_FAMILY_SEQ", sequenceName = "METRICS_FAMILY_SEQ")
    private Long id;
    @Column(nullable = false, unique = true)
    private String code;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
