package org.bambrikii.monitoring.envminidashboard.web.controllers;

import org.bambrikii.monitoring.envminidashboard.model.Tag;
import org.bambrikii.monitoring.envminidashboard.web.services.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@DashboardRestAnnotation
@RestController
@RequestMapping("/tag")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping(value = "/${id}")
    public ResponseEntity<Tag> fetch(@PathVariable("id") Long id) {
        return ResponseEntity.ok(tagService.read(id));
    }

    @GetMapping("")
    public ResponseEntity<List<Tag>> find(@RequestParam("name") String name) {
        return ResponseEntity.ok(tagService.find(name));
    }

    @PostMapping("")
    public ResponseEntity<Tag> save(@RequestBody Tag pojo) {
        return ResponseEntity.ok(tagService.update(null, pojo));
    }

    @PutMapping("/${id}")
    public ResponseEntity<Tag> save(@PathVariable("id") Long id, @RequestBody Tag pojo) {
        return ResponseEntity.ok(tagService.update(id, pojo));
    }

    @DeleteMapping("/${id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        tagService.delete(id);
        return ResponseEntity.ok(true);
    }
}
