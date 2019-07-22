package org.bambrikii.monitoring.envminidashboard.webapp.loader;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"org.bambrikii.monitoring.envminidashboard.data.config"})
@EntityScan(basePackages = {"org.bambrikii.monitoring.envminidashboard.data.config"})
public class LoaderConfig {
}
