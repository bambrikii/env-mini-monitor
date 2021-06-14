package org.bambrikii.monitoring.envminidashboard.web.controllers;

import org.bambrikii.monitoring.envminidashboard.model.PhysicalConn;
import org.bambrikii.monitoring.envminidashboard.web.services.PhysicalConnService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/conn/physical")
public class PhysicalConnController {
    private final PhysicalConnService physicalConnService;

    public PhysicalConnController(PhysicalConnService physicalConnService) {
        this.physicalConnService = physicalConnService;
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
}
