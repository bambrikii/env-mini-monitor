package org.bambrikii.monitoring.envminidashboard.web.controllers;

import org.bambrikii.monitoring.envminidashboard.model.Env;
import org.bambrikii.monitoring.envminidashboard.web.services.EnvService;
import org.bambrikii.monitoring.envminidashboard.web.services.PhysicalConnService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@DashboardRestAnnotation
@RestController
@RequestMapping("/env")
public class EnvController {
    private final EnvService envService;
    private final PhysicalConnService physicalConnService;

    public EnvController(
            EnvService envService,
            PhysicalConnService physicalConnService
    ) {
        this.envService = envService;
        this.physicalConnService = physicalConnService;
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

    @DeleteMapping("/${id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        envService.delete(id);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/${id}/env/${cnid}")
    public ResponseEntity<Void> addSshConfig(@PathVariable("id") Long id, @PathVariable("envid") Long connId) {
        envService.addConn(id, connId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/${id}/env/${cnid}")
    public ResponseEntity<Void> deleteEnv(@PathVariable("id") Long id, @PathVariable("envid") Long connId) {
        envService.deleteConn(id, connId);
        return ResponseEntity.ok().build();
    }
}
