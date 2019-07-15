package org.bambrikii.monitoring.envminidashboard.impl.connectors;

import lombok.Getter;
import lombok.Setter;
import org.bambrikii.monitoring.envminidashboard.model.ConnectionSettings;

@Getter
@Setter
public class SshConnectionSettings extends ConnectionSettings {
    private String username;
    private String password;
}
