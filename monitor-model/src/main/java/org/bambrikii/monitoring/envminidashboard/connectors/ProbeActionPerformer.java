package org.bambrikii.monitoring.envminidashboard.connectors;

import java.util.function.Supplier;

public interface ProbeActionPerformer<T, R> {
    R cmd(Supplier<T> perform);
}
