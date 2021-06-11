package org.bambrikii.monitoring.envminidashboard.model.api;

import org.bambrikii.monitoring.envminidashboard.model.PhysicalConn;

import java.util.List;

public interface Environmentable<P extends PhysicalConnectable> {
    String getCode();

    void setCode(String code);

    List<P> getConnections();

    void setConnections(List<PhysicalConn> connections);
}
