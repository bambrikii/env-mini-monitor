package org.bambrikii.monitoring.envminidashboard.web;

import org.bambrikii.monitoring.envminidashboard.model.Dashboard;
import org.bambrikii.monitoring.envminidashboard.model.Env;
import org.bambrikii.monitoring.envminidashboard.web.config.EnvMonDashboardConfig;
import org.bambrikii.monitoring.envminidashboard.web.controllers.DashboardController;
import org.bambrikii.monitoring.envminidashboard.web.controllers.EnvController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@Transactional
@ContextConfiguration(classes = {
        EnvMonDashboardConfig.class
})
@AutoConfigureDataJpa
@RunWith(SpringRunner.class)
@WebAppConfiguration
@AutoConfigureMockMvc
@RestClientTest(DashboardController.class)
@AutoConfigureWebClient(registerRestTemplate = true)
public class DashboardControllerTest {
    @Autowired
    private DashboardController client;

    @Autowired
    private EnvController envClient;

    @Autowired
    private MockRestServiceServer server;

    @Test
    public void shouldRespond() throws Exception {
        this.server
                .expect(requestTo("/env-monitor/dashboard"))
                .andRespond(withSuccess())
        ;
    }

    private Env saveEnv(String code) {
        Env env = new Env();
        env.setCode(code);
        return envClient.save(env).getBody();
    }

    @Test
    public void shouldAddDashboard() {
        Env envResponse = saveEnv("env1");

        Dashboard request = new Dashboard();
        request.setName("dashboard1");
        request.getEnvs().add(envResponse);
        client.save(request);

        ResponseEntity<List<Dashboard>> response = client.find(null);
        assertThat(response).isNotNull();

        List<Dashboard> body = response.getBody();
        assertThat(body).isNotNull().hasSize(1);
        Dashboard dashboard = body.get(0);
        assertThat(dashboard).extracting("name").contains("dashboard1");
        assertThat(dashboard).extracting("envs").isNotNull().hasSize(1);
        assertThat(dashboard.getEnvs().get(0)).extracting("code").contains("env1");
    }

    @Test
    public void shouldAddToEnvs() {
        Dashboard request = new Dashboard();
        request.setName("dashboard1");
        request.getEnvs().add(saveEnv("env1"));
        client.save(request);

        List<Dashboard> dashboardList = client.find("dashboard1").getBody();
        assertThat(dashboardList).hasSize(1);
        Dashboard dashboard2 = dashboardList.get(0);
        assertThat(dashboard2).extracting("envs").hasSize(1);

        request.setId(dashboard2.getId());
        request.setEnvs(dashboard2.getEnvs());
        request.getEnvs().add(saveEnv("env2"));
        client.save(request);

        dashboardList = client.find("dashboard1").getBody();
        assertThat(dashboardList).hasSize(1);
        dashboard2 = dashboardList.get(0);
        assertThat(dashboard2.getEnvs()).hasSize(2);
        List<Env> envs2 = dashboard2.getEnvs();
        assertThat(envs2.get(0)).extracting("code").contains("env1");
        assertThat(envs2.get(1)).extracting("code").contains("env2");
    }

    @Test
    public void shouldAddEnvOnce() {
        Dashboard request = new Dashboard();
        request.setName("dashboard1");
        request.getEnvs().add(saveEnv("env1"));
        client.save(request);

        List<Dashboard> dashboardList = client.find("dashboard1").getBody();
        assertThat(dashboardList).hasSize(1);
        Dashboard dashboard2 = dashboardList.get(0);
        assertThat(dashboard2).extracting("envs").hasSize(1);

        request.setId(dashboard2.getId());
        request.setEnvs(Arrays.asList(saveEnv("env2")));
        client.save(request);

        dashboardList = client.find("dashboard1").getBody();
        assertThat(dashboardList).hasSize(1);
        dashboard2 = dashboardList.get(0);
        List<Env> envs = dashboard2.getEnvs();
        assertThat(envs).hasSize(1);
        assertThat(envs.get(0)).extracting("code").contains("env2");
    }

}
