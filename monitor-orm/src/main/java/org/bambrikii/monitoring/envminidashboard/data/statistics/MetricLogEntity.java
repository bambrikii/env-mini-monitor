package org.bambrikii.monitoring.envminidashboard.data.statistics;

import org.bambrikii.monitoring.envminidashboard.model.api.MetricLoggable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Calendar;

@Entity
@Table
public class MetricLogEntity implements MetricLoggable {
    @Id
    @GeneratedValue(generator = "METRIC_LOG", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "METRIC_LOG_SEQ", sequenceName = "METRIC_LOG_SEQ")
    private Long id;

    @Column(nullable = false)
    private String code;

    @Column
    private Object value;

    @Column
    private Calendar time;

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

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }
}
