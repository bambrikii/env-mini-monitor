package org.bambrikii.monitoring.envminidashboard.webapp.converters;

import org.bambrikii.monitoring.envminidashboard.data.config.MetricsFamilyConfigEntity;
import org.bambrikii.monitoring.envminidashboard.model.MetricsNamespace;

public class MetricsNamespaceConverter {
    public static MetricsNamespace convert(MetricsFamilyConfigEntity entity) {
        MetricsNamespace namespace = new MetricsNamespace();
        namespace.setCode(entity.getCode());
        return namespace;
    }
}
