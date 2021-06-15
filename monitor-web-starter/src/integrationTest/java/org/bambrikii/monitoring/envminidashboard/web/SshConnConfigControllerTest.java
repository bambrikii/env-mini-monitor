package org.bambrikii.monitoring.envminidashboard.web;

import org.bambrikii.monitoring.envminidashboard.impl.connectors.ssh.SshConnConfig;
import org.bambrikii.monitoring.envminidashboard.web.config.EnvMonDashboardConfig;
import org.bambrikii.monitoring.envminidashboard.web.controllers.SshConnConfigController;
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

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@ContextConfiguration(classes = {
        EnvMonDashboardConfig.class
})
@AutoConfigureDataJpa
@RunWith(SpringRunner.class)
@WebAppConfiguration
@AutoConfigureMockMvc
@RestClientTest(SshConnConfigController.class)
@AutoConfigureWebClient(registerRestTemplate = true)
public class SshConnConfigControllerTest {
    @Autowired
    private SshConnConfigController client;

    @Autowired
    private MockRestServiceServer server;

    @Test
    public void shouldPersist() {
        assertThat(client).isNotNull();

        SshConnConfig request = new SshConnConfig();
        request.setHost("host1");
        request.setPort(1);
        request.setUsername("username1");
        request.setPassword("password1");
        client.save(request);

        ResponseEntity<SshConnConfig> response = client.save(request);
        assertThat(response).isNotNull();

        SshConnConfig body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body)
                .hasFieldOrPropertyWithValue("host", "host1")
                .hasFieldOrPropertyWithValue("port", 1)
                .hasFieldOrPropertyWithValue("username", "username1")
                .hasFieldOrPropertyWithValue("password", "password1")
        ;
    }

}
