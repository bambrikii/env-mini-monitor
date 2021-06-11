package org.bambrikii.monitoring.envminidashboard.webapp.converters;

import org.bambrikii.monitoring.envminidashboard.data.config.MetricsNamespaceEntity;
import org.bambrikii.monitoring.envminidashboard.model.MetricsNamespace;

public class MetricsNamespaceConverter {
    public static MetricsNamespace convert(MetricsNamespaceEntity entity) {
        MetricsNamespace namespace = new MetricsNamespace();
        namespace.setCode(entity.getCode());
        return namespace;
    }
}
