package org.bambrikii.monitoring.envminidashboard.orm.model;

import org.bambrikii.monitoring.envminidashboard.orm.config.EnvMonitorConfig;
import org.bambrikii.monitoring.envminidashboard.orm.services.DashboardEntityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@ContextConfiguration(classes = {
        EnvMonitorConfig.class
})
@RunWith(SpringRunner.class)
@DataJpaTest(showSql = false)
public class DashboardPersisterTest {
    @Autowired
    private DashboardEntityService dashboardEntityService;

    @Test
    public void shouldFindAll() {
        dashboardEntityService.findAll();
    }

    @Test
    public void shouldPersistModel() {
        TagEntity devTag1 = dashboardEntityService.createTag("tag1");
        SshConnConfigEntity sshConfig1 = dashboardEntityService.createSshConnConfig(devTag1, "localhost", 22);
        MetricLogEntity log1 = dashboardEntityService.createLog("code1", "val1", Calendar.getInstance());
        PhysicalConnEntity conn1 = dashboardEntityService.createConn(devTag1, sshConfig1, log1);
        EnvEntity dev = dashboardEntityService.createEnv(conn1, "dev");
        DashboardEntity dashboad = dashboardEntityService.createDashboard(dev);

        dashboardEntityService.save(dashboad);

        Optional<DashboardEntity> dashboadPersisted = dashboardEntityService.findDashboardById(dashboad.getId());

        System.out.println(dashboadPersisted);
        assertThat(dashboadPersisted.get()).isNotNull();
    }

}
