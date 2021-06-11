package org.bambrikii.monitoring.envminidashboard.data.config;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class DashboardEntity {
    @Id
    @GeneratedValue(generator = "DASHBOARD_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "DASHBOARD_SEQ", sequenceName = "DASHBOARD_SEQ")
    private Long id;
    @OneToMany
    private List<EnvEntity> envs = new ArrayList<>();

    public List<EnvEntity> getEnvs() {
        return envs;
    }

    public void setEnvs(List<EnvEntity> envs) {
        this.envs = envs;
    }
}