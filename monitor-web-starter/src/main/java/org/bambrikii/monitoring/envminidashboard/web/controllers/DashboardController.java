package org.bambrikii.monitoring.envminidashboard.web.controllers;

import org.bambrikii.monitoring.envminidashboard.model.Dashboard;
import org.bambrikii.monitoring.envminidashboard.web.services.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@DashboardRestAnnotation
@RestController("/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Dashboard>> list() {
        return ResponseEntity.ok(dashboardService.list());
    }

    @GetMapping(value = "/${id}")
    public ResponseEntity<Dashboard> fetch(@PathVariable("id") Long id) {
        return ResponseEntity.ok(dashboardService.read(id));
    }

    @PutMapping("")
    public ResponseEntity<Dashboard> save(@RequestBody Dashboard dashboard) {
        return ResponseEntity.ok(dashboardService.update(null, dashboard));
    }

    @PostMapping("/${id}")
    public ResponseEntity<Dashboard> save(@PathVariable("id") Long id, @RequestBody Dashboard dashboard) {
        return ResponseEntity.ok(dashboardService.update(id, dashboard));
    }
}
