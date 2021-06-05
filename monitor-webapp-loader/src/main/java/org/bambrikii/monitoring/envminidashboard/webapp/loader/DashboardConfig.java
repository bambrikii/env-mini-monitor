package org.bambrikii.monitoring.envminidashboard.webapp.loader;

import org.bambrikii.monitoring.envminidashboard.data.config.ConnConfigEntity;
import org.bambrikii.monitoring.envminidashboard.data.config.ConnConfigRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = {
        ConnConfigRepository.class
})
@EntityScan(basePackageClasses = {
        ConnConfigEntity.class
})
public class DashboardConfig {
}
