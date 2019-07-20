package org.bambrikii.monitoring.envminidashboard.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MetricsFamily {
    @Setter(AccessLevel.NONE)
    private String code;

    public MetricsFamily(String code) {
        this.code = code;
    }
}
