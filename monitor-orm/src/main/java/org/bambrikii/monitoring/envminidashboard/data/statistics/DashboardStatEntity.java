package org.bambrikii.monitoring.envminidashboard.data.statistics;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
public class DashboardStatEntity {
    @Id
    @GeneratedValue(generator = "DASHBOARD_STAT_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "DASHBOARD_STAT_SEQ", sequenceName = "DASHBOARD_STAT_SEQ")
    private Long id;
    @OneToMany
    private List<MetricLogEntity> statistics;
}
