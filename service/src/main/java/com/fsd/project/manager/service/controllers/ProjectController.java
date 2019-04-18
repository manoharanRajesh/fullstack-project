package com.fsd.project.manager.service.controllers;

import com.fsd.project.manager.service.service.ProjectService;
import com.fsd.project.manager.service.view.ProjectModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    ProjectService service;

    @GetMapping
    public List<ProjectModel> getTasks() {
        return service.get();
    }

    @GetMapping("/{id}")
    public ProjectModel getTask(@PathVariable(value = "id") String id) {
        return service.get(id);
    }

    @PostMapping
    public void add(@RequestBody ProjectModel model) {
        service.update(model);
    }


    @PostMapping("/delete")
    public ResponseEntity delete(@RequestBody String id) {
        if (id != null) {
            service.delete(id);
        }
        return ResponseEntity.ok().build();

    }


}
