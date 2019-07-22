package org.bambrikii.monitoring.envminidashboard.data.config;

import lombok.Getter;
import lombok.Setter;
import org.bambrikii.monitoring.envminidashboard.model.Dashboardable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class DashboardConfig implements Dashboardable {
    @Id
    @GeneratedValue(generator = "DASHBOARD_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "DASHBOARD_SEQ", sequenceName = "DASHBOARD_SEQ")
    private Long id;
    @OneToMany
    private List<EnvironmentConfig> environments = new ArrayList<>();
}
