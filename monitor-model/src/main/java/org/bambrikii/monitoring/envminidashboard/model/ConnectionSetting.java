package org.bambrikii.monitoring.envminidashboard.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ConnectionSetting implements ConnectionSettingable {
    private String host;
    private int port = 22;
}
