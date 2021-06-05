package org.bambrikii.monitoring.envminidashboard.webapp.loader;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@DashboardAnnotation
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @RequestMapping("/dashboard")
    public void dashboard1() {
    }
}
