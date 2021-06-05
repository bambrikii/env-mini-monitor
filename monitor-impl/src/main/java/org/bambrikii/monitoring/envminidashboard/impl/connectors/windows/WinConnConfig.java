package org.bambrikii.monitoring.envminidashboard.impl.connectors.windows;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.bambrikii.monitoring.envminidashboard.model.HostConnConfig;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class WinConnConfig extends HostConnConfig {
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
