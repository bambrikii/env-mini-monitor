package org.bambrikii.monitoring.envminidashboard.dashboard;

import org.bambrikii.monitoring.envminidashboard.connectors.ConnectionPool;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.ssh.SshConnectionSetting;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.ssh.SshConnector;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.windows.WindowsConnectionSetting;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.windows.WindowsConnector;
import org.bambrikii.monitoring.envminidashboard.impl.loader.LinuxSysMetricsLoader;
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

        // My Laptop
        Tag myLaptopTag = new Tag();
        myLaptopTag.setName("myLaptop");

        WindowsConnectionSetting localConnectionSetting = new WindowsConnectionSetting();

        // My Vm
        Tag myVmTag = new Tag();
        myVmTag.setName("myVm");

        SshConnectionSetting vmConnectionSetting = new SshConnectionSetting();
        vmConnectionSetting.setHost(System.getenv("MY_VM1_HOSTNAME"));
        vmConnectionSetting.setUsername(System.getenv("MY_VM1_USERNAME"));
        vmConnectionSetting.setPassword(System.getenv("MY_VM1_PASSWORD"));

        dashboardBuilder
                .env("local")

                .metricsFamily(SYS_METRICS_FAMILY, myLaptopTag)
                .metricsFamily(APP_LOGS_METRICS_FAMILY, myLaptopTag)
                .connectionSettings(localConnectionSetting, myLaptopTag)

                .metricsFamily(SYS_METRICS_FAMILY, myVmTag)
                .connectionSettings(vmConnectionSetting, myVmTag)

                .metricsFamilyLoader(new WindowsSysMetricsLoader())
                .metricsFamilyLoader(new WindowsAppLogsMetricsLoader())

                .metricsFamilyLoader(new LinuxSysMetricsLoader())
        ;

        Dashboard dashboard = dashboardBuilder.buildDashboard();

        ConnectionPool connectionPool = new ConnectionPool();
        connectionPool.
                register(localConnectionSetting, new WindowsConnector())
                .register(vmConnectionSetting, new SshConnector());

        DashboardLoader dashboardLoader = new DashboardLoader(connectionPool);
        DashboardResult dashboardResult = dashboardLoader.load(dashboard);

        assertNotNull(dashboardResult);
        System.out.println(dashboardResult.toString());
    }
}
