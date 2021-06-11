package org.bambrikii.monitoring.envminidashboard.impl.dashboard;

import org.bambrikii.monitoring.envminidashboard.model.ConnConfig;
import org.bambrikii.monitoring.envminidashboard.model.Dashboard;
import org.bambrikii.monitoring.envminidashboard.model.Env;
import org.bambrikii.monitoring.envminidashboard.model.MetricLog;
import org.bambrikii.monitoring.envminidashboard.model.MetricsNamespace;
import org.bambrikii.monitoring.envminidashboard.model.PhysicalConn;
import org.bambrikii.monitoring.envminidashboard.model.Tag;
import org.bambrikii.monitoring.envminidashboard.model.api.Dashboardable;

public class DashboardBuilder extends AbstractDashboardBuilder<
        Env,
        PhysicalConn,
        Tag,
        ConnConfig,
        MetricLog,
        MetricsNamespace
        > {
    @Override
    protected Env newEnv() {
        return new Env();
    }

    @Override
    protected PhysicalConn newPhysicalConn() {
        return new PhysicalConn();
    }

    @Override
    protected Dashboardable<Env> newDashboard() {
        return new Dashboard();
    }
}
