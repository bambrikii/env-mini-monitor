package org.bambrikii.monitoring.envminidashboard.model.api;

import java.util.List;

public interface Dashboardable<E extends Environmentable> {
    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    List<E> getEnvs();

    void setEnvs(List<E> envs);
}
