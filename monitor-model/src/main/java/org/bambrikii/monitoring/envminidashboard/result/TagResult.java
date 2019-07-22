package org.bambrikii.monitoring.envminidashboard.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class TagResult {
    private String name;
    private List<ConnectionResult> connections = new ArrayList<>();

    public ConnectionResult addConnection(String type, String name) {
        ConnectionResult connectionResult = new ConnectionResult();
        connectionResult.setConnectionType(type);
        connectionResult.setConnectionName(name);

        connections.add(connectionResult);

        return connectionResult;
    }
}
