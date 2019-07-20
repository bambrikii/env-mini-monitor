package org.bambrikii.monitoring.envminidashboard.impl.connectors;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.bambrikii.monitoring.envminidashboard.model.ConnectionSetting;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class HttpConnectionSetting extends ConnectionSetting {
    private String urlPath;
    private String method;
}
