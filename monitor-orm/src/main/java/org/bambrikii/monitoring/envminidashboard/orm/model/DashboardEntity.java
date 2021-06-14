package org.bambrikii.monitoring.envminidashboard.orm.model;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@ToString
@Entity
@Table
public class DashboardEntity {
    @Id
    @GeneratedValue(generator = "DASHBOARD_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "DASHBOARD_SEQ", sequenceName = "DASHBOARD_SEQ")
    private Long id;
    @Column(unique = true)
    private String name;
    @OneToMany
    private List<EnvEntity> envs = new ArrayList<>();

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

    public List<EnvEntity> getEnvs() {
        return envs;
    }

    public void setEnvs(List<EnvEntity> envs) {
        this.envs = envs;
    }
}
