package org.bambrikii.monitoring.envminidashboard.webapp.view;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@MonitorRestAnnotation
public class DashboardController {
    @RequestMapping("/dashboard")
    public void dashboard1() {

    }
}
