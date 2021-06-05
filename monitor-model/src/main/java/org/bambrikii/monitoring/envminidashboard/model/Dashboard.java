package org.bambrikii.monitoring.envminidashboard.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Dashboard implements Dashboardable {
    private List<Environmentable> envs = new ArrayList<>();

    @Override
    public List getEnvs() {
        return envs;
    }

    public void setEnvs(List<Environmentable> envs) {
        this.envs = envs;
    }
}
