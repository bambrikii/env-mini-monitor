package org.bambrikii.monitoring.envminidashboard.orm.config;

import org.bambrikii.monitoring.envminidashboard.orm.model.DashboardEntity;
import org.bambrikii.monitoring.envminidashboard.orm.model.DashboardRepository;
import org.bambrikii.monitoring.envminidashboard.orm.services.DashboardEntityService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackageClasses = {
        DashboardEntity.class
})
@EnableJpaRepositories(basePackageClasses = {
        DashboardRepository.class
})
@Import({
        DashboardEntityService.class
})
@Configuration
public class MonitorConfig {
}

