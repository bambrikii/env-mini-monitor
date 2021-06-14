package org.bambrikii.monitoring.envminidashboard.web.converters;

import org.bambrikii.monitoring.envminidashboard.orm.model.MetricsNamespaceEntity;
import org.bambrikii.monitoring.envminidashboard.model.MetricsNamespace;

public class MetricsNamespaceConverter {
    public static MetricsNamespace convert(MetricsNamespaceEntity entity) {
        MetricsNamespace namespace = new MetricsNamespace();
        namespace.setCode(entity.getCode());
        return namespace;
    }
}
