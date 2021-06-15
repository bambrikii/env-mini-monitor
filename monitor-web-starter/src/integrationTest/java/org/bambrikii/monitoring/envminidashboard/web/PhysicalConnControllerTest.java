package org.bambrikii.monitoring.envminidashboard.web;

import org.bambrikii.monitoring.envminidashboard.impl.connectors.ssh.SshConnConfig;
import org.bambrikii.monitoring.envminidashboard.model.PhysicalConn;
import org.bambrikii.monitoring.envminidashboard.model.Tag;
import org.bambrikii.monitoring.envminidashboard.web.config.EnvMonDashboardConfig;
import org.bambrikii.monitoring.envminidashboard.web.controllers.PhysicalConnController;
import org.bambrikii.monitoring.envminidashboard.web.controllers.SshConnConfigController;
import org.bambrikii.monitoring.envminidashboard.web.controllers.TagController;
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

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@ContextConfiguration(classes = {
        EnvMonDashboardConfig.class
})
@AutoConfigureDataJpa
@RunWith(SpringRunner.class)
@WebAppConfiguration
@AutoConfigureMockMvc
@RestClientTest(PhysicalConnController.class)
@AutoConfigureWebClient(registerRestTemplate = true)
public class PhysicalConnControllerTest {
    @Autowired
    private PhysicalConnController client;
    @Autowired
    private SshConnConfigController sshConfigClient;
    @Autowired
    private TagController tagClient;

    @Autowired
    private MockRestServiceServer server;

    @Test
    public void shouldPersist() {
        assertThat(client).isNotNull();

        SshConnConfig sshConfig = new SshConnConfig();
        sshConfig.setHost("host1");
        sshConfig.setPort(1);
        sshConfig.setUsername("user1");
        sshConfig.setPassword("password1");
        SshConnConfig sshConfigResponse = sshConfigClient.save(sshConfig).getBody();

        Tag tag1 = new Tag();
        tag1.setName("tag1");
        Tag tag1Response = tagClient.save(tag1).getBody();

        PhysicalConn request = new PhysicalConn();
        request.setConfig(sshConfigResponse);
        request.setTags(Arrays.asList(tag1Response));
        client.save(request);

        ResponseEntity<PhysicalConn> response = client.save(request);
        assertThat(response).isNotNull();

        PhysicalConn body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body).extracting("config.id").isNotNull();
        assertThat(body).extracting("tags").hasSize(1);
        Tag tag1Assert = body.getTags().get(0);
        assertThat(tag1Assert).isEqualTo(tag1Response);
    }
}
