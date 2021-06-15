package org.bambrikii.monitoring.envminidashboard.web;

import org.bambrikii.monitoring.envminidashboard.model.Env;
import org.bambrikii.monitoring.envminidashboard.web.config.EnvMonDashboardConfig;
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

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@ContextConfiguration(classes = {
        EnvMonDashboardConfig.class
})
@AutoConfigureDataJpa
@RunWith(SpringRunner.class)
@WebAppConfiguration
@AutoConfigureMockMvc
@RestClientTest(EnvController.class)
@AutoConfigureWebClient(registerRestTemplate = true)
public class EnvControllerTest {
    @Autowired
    private EnvController client;

    @Autowired
    private MockRestServiceServer server;

    @Test
    public void shouldPersist() {
        assertThat(client).isNotNull();

        Env request = new Env();
        request.setCode("name1");
        client.save(request);

        ResponseEntity<Env> response = client.save(request);
        assertThat(response).isNotNull();

        Env body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body).extracting("code").contains("name1");
    }

}
