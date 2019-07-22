package org.bambrikii.monitoring.envminidashboard.impl.connectors.ssh;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.bambrikii.monitoring.envminidashboard.model.ConnectionSetting;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class SshConnectionSetting extends ConnectionSetting {
    private String username;
    private String password;
}