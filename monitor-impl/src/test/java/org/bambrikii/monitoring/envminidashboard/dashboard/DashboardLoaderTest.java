package org.bambrikii.monitoring.envminidashboard.dashboard;

import org.bambrikii.monitoring.envminidashboard.impl.connectors.ssh.SshConnector;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.ssh.SshHostConnConfigCfg;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.windows.SmbConnector;
import org.bambrikii.monitoring.envminidashboard.impl.connectors.windows.WinConnConfig;
import org.bambrikii.monitoring.envminidashboard.impl.dashboard.DashboardBuilder;
import org.bambrikii.monitoring.envminidashboard.impl.dashboard.DashboardLoader;
import org.bambrikii.monitoring.envminidashboard.impl.loader.LinuxSysProbe;
import org.bambrikii.monitoring.envminidashboard.impl.loader.WinSysProbe;
import org.bambrikii.monitoring.envminidashboard.impl.loader.WindowsAppLogsProbe;
import org.bambrikii.monitoring.envminidashboard.model.Tag;
import org.bambrikii.monitoring.envminidashboard.model.api.Dashboardable;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

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

        SshHostConnConfigCfg connCfg = new SshHostConnConfigCfg();
        connCfg.setHost(System.getenv("MY_VM1_HOSTNAME"));
        connCfg.setUsername(System.getenv("MY_VM1_USERNAME"));
        connCfg.setPassword(System.getenv("MY_VM1_PASSWORD"));

        builder
                .probe(new WinSysProbe())
                .probe(new WindowsAppLogsProbe())
                .probe(new LinuxSysProbe())

                .env("local")

                .conn(localConnConfig, myLaptopTag) // my laptop
                .conn(connCfg, myVmTag) // my vm

                .connector(localConnConfig, new SmbConnector())
                .connector(connCfg, new SshConnector());

        ;

        Dashboardable model = builder.buildModel();
        DashboardLoader loader = builder.buildImpl();
        loader.load(model);

        log.info(model.toString());

        assertNotNull(model);
    }
}
