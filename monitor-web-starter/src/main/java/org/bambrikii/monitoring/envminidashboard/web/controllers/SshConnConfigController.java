package org.bambrikii.monitoring.envminidashboard.web.controllers;

import org.bambrikii.monitoring.envminidashboard.impl.connectors.ssh.SshConnConfig;
import org.bambrikii.monitoring.envminidashboard.web.services.SshConnConfigService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/conn/config/ssh")
public class SshConnConfigController {
    private final SshConnConfigService sshConnConfigService;

    public SshConnConfigController(SshConnConfigService sshConnConfigService) {
        this.sshConnConfigService = sshConnConfigService;
    }

    @GetMapping(value = "/${id}")
    public ResponseEntity<SshConnConfig> fetch(@PathVariable("id") Long id) {
        return ResponseEntity.ok(sshConnConfigService.read(id));
    }

    @PostMapping("")
    public ResponseEntity<SshConnConfig> save(@RequestBody SshConnConfig pojo) {
        return ResponseEntity.ok(sshConnConfigService.update(null, pojo));
    }

    @PutMapping("/${id}")
    public ResponseEntity<SshConnConfig> save(@PathVariable("id") Long id, @RequestBody SshConnConfig pojo) {
        return ResponseEntity.ok(sshConnConfigService.update(id, pojo));
    }
}
