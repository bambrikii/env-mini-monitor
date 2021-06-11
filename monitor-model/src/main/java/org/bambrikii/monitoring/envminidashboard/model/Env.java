package org.bambrikii.monitoring.envminidashboard.model;

import org.bambrikii.monitoring.envminidashboard.model.api.Environmentable;

import java.util.ArrayList;
import java.util.List;

public class Env implements Environmentable<PhysicalConn> {
    private String code;
    private List<PhysicalConn> connections = new ArrayList<>();

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public List<PhysicalConn> getConnections() {
        return connections;
    }

    @Override
    public void setConnections(List<PhysicalConn> connections) {
        this.connections = connections;
    }

    @Override
    public String toString() {
        return "Env{" + "code='" + code + '\'' + ", connections=" + connections + '}';
    }
}
