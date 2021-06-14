package org.bambrikii.monitoring.envminidashboard.web.config;

import org.bambrikii.monitoring.envminidashboard.orm.config.EnvMonitorConfig;
import org.bambrikii.monitoring.envminidashboard.web.controllers.DashboardController;
import org.bambrikii.monitoring.envminidashboard.web.converters.DashboardConverter;
import org.bambrikii.monitoring.envminidashboard.web.services.DashboardService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        EnvMonitorConfig.class
})
@ComponentScan(basePackageClasses = {
        DashboardController.class,
        DashboardConverter.class,
        DashboardService.class
})
public class EnvMonDashboardConfig {
}
