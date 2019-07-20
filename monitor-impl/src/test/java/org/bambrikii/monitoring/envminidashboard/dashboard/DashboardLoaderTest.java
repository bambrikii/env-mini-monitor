package org.bambrikii.monitoring.envminidashboard.dashboard;

import org.bambrikii.monitoring.envminidashboard.connectors.ConnectionPool;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.WindowsConnectionSetting;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.WindowsConnector;
import org.bambrikii.monitoring.envminidashboard.impl.loader.WindowsAppLogsMetricsLoader;
import org.bambrikii.monitoring.envminidashboard.impl.loader.WindowsSysMetricsLoader;
import org.bambrikii.monitoring.envminidashboard.model.Dashboard;
import org.bambrikii.monitoring.envminidashboard.model.Tag;
import org.bambrikii.monitoring.envminidashboard.result.DashboardResult;
import org.junit.Test;

import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsFactory.APP_LOGS_METRICS_FAMILY;
import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsFactory.SYS_METRICS_FAMILY;
import static org.junit.Assert.assertNotNull;

public class DashboardLoaderTest {
    @Test
    public void shouldLoadLocalMetrics() {
        DashboardBuilder dashboardBuilder = new DashboardBuilder();
        Tag myLaptopTag = new Tag();
        myLaptopTag.setName("myLaptop");
        WindowsConnectionSetting localConnectionSetting = new WindowsConnectionSetting();
        dashboardBuilder
                .env("local")

                .metricsFamily(SYS_METRICS_FAMILY, myLaptopTag)
                .metricsFamily(APP_LOGS_METRICS_FAMILY, myLaptopTag)
                .connectionSettings(localConnectionSetting, myLaptopTag)

                .metricsFamilyLoader(new WindowsSysMetricsLoader())
                .metricsFamilyLoader(new WindowsAppLogsMetricsLoader());

        Dashboard dashboard = dashboardBuilder.buildDashboard();

        ConnectionPool connectionPool = new ConnectionPool();
        connectionPool.register(localConnectionSetting, new WindowsConnector());

        DashboardLoader dashboardLoader = new DashboardLoader(connectionPool);
        DashboardResult dashboardResult = dashboardLoader.load(dashboard);

        assertNotNull(dashboardResult);
    }
}
