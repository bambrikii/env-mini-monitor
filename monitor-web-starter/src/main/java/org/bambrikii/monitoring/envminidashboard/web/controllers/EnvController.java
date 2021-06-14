package org.bambrikii.monitoring.envminidashboard.web.controllers;

import org.bambrikii.monitoring.envminidashboard.model.Env;
import org.bambrikii.monitoring.envminidashboard.web.services.EnvService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@DashboardRestAnnotation
@RestController("/env")
public class EnvController {
    private final EnvService envService;

    public EnvController(EnvService envService) {
        this.envService = envService;
    }

    @GetMapping(value = "/${id}")
    public ResponseEntity<Env> fetch(@PathVariable("id") Long id) {
        return ResponseEntity.ok(envService.read(id));
    }

    @PostMapping("")
    public ResponseEntity<Env> save(@RequestBody Env pojo) {
        return ResponseEntity.ok(envService.update(null, pojo));
    }

    @PutMapping("/${id}")
    public ResponseEntity<Env> save(@PathVariable("id") Long id, @RequestBody Env pojo) {
        return ResponseEntity.ok(envService.update(id, pojo));
    }
}
