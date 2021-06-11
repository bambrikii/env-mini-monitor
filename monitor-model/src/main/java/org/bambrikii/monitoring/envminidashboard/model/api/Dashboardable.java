package org.bambrikii.monitoring.envminidashboard.model.api;

import java.util.List;

public interface Dashboardable<E extends Environmentable> {
    List<E> getEnvs();

    void setEnvs(List<E> envs);
}
