package org.bambrikii.monitoring.envminidashboard.dashboard;

import org.bambrikii.monitoring.envminidashboard.impl.connectors.ssh.SshConnector;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.ssh.SshHostConnCfg;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.windows.SmbConnector;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.windows.WinConnConfig;
import org.bambrikii.monitoring.envminidashboard.impl.dashboard.DashboardBuilder;
import org.bambrikii.monitoring.envminidashboard.impl.dashboard.DashboardLoader;
import org.bambrikii.monitoring.envminidashboard.impl.loader.LinuxSysProbe;
import org.bambrikii.monitoring.envminidashboard.impl.loader.WinSysProbe;
import org.bambrikii.monitoring.envminidashboard.impl.loader.WindowsAppLogsProbe;
import org.bambrikii.monitoring.envminidashboard.model.Dashboardable;
import org.bambrikii.monitoring.envminidashboard.model.Tag;
import org.bambrikii.monitoring.envminidashboard.result.DashboardResult;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsRegistry.APP_LOGS_NAMESPACE;
import static org.bambrikii.monitoring.envminidashboard.impl.metrics.MetricsRegistry.SYS_NAMESPACE;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DashboardLoaderTest {
    private static final Logger log = Logger.getLogger(DashboardLoaderTest.class.getName());

    @Test
    public void shouldLoadLocalMetrics() {
        DashboardBuilder builder = new DashboardBuilder();

        // My Laptop
        Tag myLaptopTag = new Tag();
        myLaptopTag.setName("myLaptop");

        WinConnConfig localConnConfig = new WinConnConfig();

        // My Vm
        Tag myVmTag = new Tag();
        myVmTag.setName("myVm");

        SshHostConnCfg connCfg = new SshHostConnCfg();
        connCfg.setHost(System.getenv("MY_VM1_HOSTNAME"));
        connCfg.setUsername(System.getenv("MY_VM1_USERNAME"));
        connCfg.setPassword(System.getenv("MY_VM1_PASSWORD"));

        builder
                .probe(new WinSysProbe())
                .probe(new WindowsAppLogsProbe())
                .probe(new LinuxSysProbe())

                .env("local")

                // my laptop
                .tag(SYS_NAMESPACE, myLaptopTag)
                .tag(APP_LOGS_NAMESPACE, myLaptopTag)
                .tag(localConnConfig, myLaptopTag)

                // my vm
                .tag(SYS_NAMESPACE, myVmTag)
                .tag(connCfg, myVmTag)

                .connector(localConnConfig, new SmbConnector())
                .connector(connCfg, new SshConnector());

        ;

        Dashboardable dashboardModel = builder.buildModel();
        DashboardLoader dashboardImpl = builder.buildImpl();
        DashboardResult result = dashboardImpl.load(dashboardModel);

        log.info(result.toString());
        assertNotNull(result);
    }
}
