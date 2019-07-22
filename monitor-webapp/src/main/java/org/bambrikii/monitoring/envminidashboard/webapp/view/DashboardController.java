package org.bambrikii.monitoring.envminidashboard.webapp.view;

import org.bambrikii.monitoring.envminidashboard.webapp.loader.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@MonitorRestAnnotation
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;

    @RequestMapping("/dashboard")
    public void dashboard1() {

    }
}
