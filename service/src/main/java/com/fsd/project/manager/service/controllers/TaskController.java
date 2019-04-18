package com.fsd.project.manager.service.controllers;

import com.fsd.project.manager.service.service.TaskService;
import com.fsd.project.manager.service.view.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    TaskService service;

    @GetMapping
    public List<TaskModel> get() {
        return service.get();
    }

    @GetMapping("/{id}")
    public TaskModel get(@PathVariable(value = "id") String id) {
        return service.get(id);
    }


    @PostMapping
    public void add(@RequestBody TaskModel task) {
        service.update(task);
    }


    @PostMapping("/delete")
    public ResponseEntity delete(@RequestBody String id) {
        if (id != null) {
            service.delete(id);
        }
        return ResponseEntity.ok().build();

    }


}
