package org.bambrikii.monitoring.envminidashboard.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Environment {
    private List<ConnectionSettings> connectionSettings = new ArrayList<>();

    public Environment addHost(ConnectionSettings connectionSettings) {
        this.connectionSettings.add(connectionSettings);
        return this;
    }
}
