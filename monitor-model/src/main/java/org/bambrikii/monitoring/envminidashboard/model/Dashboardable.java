package org.bambrikii.monitoring.envminidashboard.model;

import java.util.List;

public interface Dashboardable<E extends Environmentable> {
    List<E> getEnvironments();
}
