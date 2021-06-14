package org.bambrikii.monitoring.envminidashboard.web.controllers;

import org.bambrikii.monitoring.envminidashboard.model.PhysicalConn;
import org.bambrikii.monitoring.envminidashboard.web.services.HttpConnConfigService;
import org.bambrikii.monitoring.envminidashboard.web.services.PhysicalConnService;
import org.bambrikii.monitoring.envminidashboard.web.services.SshConnConfigService;
import org.bambrikii.monitoring.envminidashboard.web.services.WinConnConfigService;
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
@RequestMapping("/conn/physical")
public class PhysicalConnController {
    private final PhysicalConnService physicalConnService;
    private final SshConnConfigService sshConnConfigService;
    private final WinConnConfigService winConnConfigService;
    private final HttpConnConfigService httpConnConfigService;

    public PhysicalConnController(
            PhysicalConnService physicalConnService,
            SshConnConfigService sshConnConfigService,
            WinConnConfigService winConnConfigService,
            HttpConnConfigService httpConnConfigService
    ) {
        this.physicalConnService = physicalConnService;
        this.sshConnConfigService = sshConnConfigService;
        this.winConnConfigService = winConnConfigService;
        this.httpConnConfigService = httpConnConfigService;
    }

    @GetMapping(value = "/${id}")
    public ResponseEntity<PhysicalConn> read(@PathVariable("id") Long id) {
        return ResponseEntity.ok(physicalConnService.read(id));
    }

    @PostMapping("")
    public ResponseEntity<PhysicalConn> save(@RequestBody PhysicalConn pojo) {
        return ResponseEntity.ok(physicalConnService.update(null, pojo));
    }

    @PutMapping("/${id}")
    public ResponseEntity<PhysicalConn> save(@PathVariable("id") Long id, @RequestBody PhysicalConn pojo) {
        return ResponseEntity.ok(physicalConnService.update(id, pojo));
    }

    @DeleteMapping("/${id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        physicalConnService.delete(id);
        return ResponseEntity.ok(true);
    }

    // SSH
    public ResponseEntity<Boolean> addSsh(@PathVariable("id") Long id, @PathVariable("confid") Long confId) {
        physicalConnService.addSsh(id, confId);
        return ResponseEntity.ok(true);
    }

    public ResponseEntity<Boolean> deleteSsh(@PathVariable("id") Long id, @PathVariable("confid") Long confId) {
        physicalConnService.deleteSsh(id, confId);
        return ResponseEntity.ok(true);
    }

    // Win
    public ResponseEntity<Boolean> addWin(@PathVariable("id") Long id, @PathVariable("confid") Long confId) {
        physicalConnService.addWin(id, confId);
        return ResponseEntity.ok(true);
    }

    public ResponseEntity<Boolean> deleteWin(@PathVariable("id") Long id, @PathVariable("confid") Long confId) {
        physicalConnService.deleteWin(id, confId);
        return ResponseEntity.ok(true);
    }

    // HTTP
    public ResponseEntity<Boolean> addHttp(@PathVariable("id") Long id, @PathVariable("confid") Long confId) {
        physicalConnService.addHttp(id, confId);
        return ResponseEntity.ok(true);
    }

    public ResponseEntity<Boolean> deleteHttp(@PathVariable("id") Long id, @PathVariable("confid") Long confId) {
        physicalConnService.deleteHttp(id, confId);
        return ResponseEntity.ok(true);
    }
}
