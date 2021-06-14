package org.bambrikii.monitoring.envminidashboard.model;

import org.bambrikii.monitoring.envminidashboard.model.api.Dashboardable;

import java.util.ArrayList;
import java.util.List;

public class Dashboard implements Dashboardable<Env> {
    private String name;
    private List<Env> envs = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Env> getEnvs() {
        return envs;
    }

    public void setEnvs(List<Env> envs) {
        this.envs = envs;
    }

    @Override
    public String toString() {
        return "Dashboard{" + "envs=" + envs + '}';
    }
}
