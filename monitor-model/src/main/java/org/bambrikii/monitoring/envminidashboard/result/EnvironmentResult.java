package org.bambrikii.monitoring.envminidashboard.result;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EnvironmentResult {
    private String code;
    private List<ConnectionResult> connections = new ArrayList<>();

    public ConnectionResult addConnection() {
        ConnectionResult connectionResult = new ConnectionResult();
        connections.add(connectionResult);
        return connectionResult;
    }
}
