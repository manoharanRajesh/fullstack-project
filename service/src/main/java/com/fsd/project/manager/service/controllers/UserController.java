package com.fsd.project.manager.service.controllers;

import com.fsd.project.manager.service.service.UserService;
import com.fsd.project.manager.service.view.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService usrService;

    @GetMapping
    public List<UserModel> getTasks() {
        return usrService.get();
    }

    @GetMapping("/{id}")
    public UserModel getTask(@PathVariable(value = "id") String id) {
        return usrService.get(id);
    }

    @PostMapping
    public void add(@RequestBody UserModel task) {
        usrService.update(task);
    }


    @PostMapping("/delete")
    public ResponseEntity delete(@RequestBody String empId) {
        if (empId != null) {
            usrService.delete(empId);
        }
        return ResponseEntity.ok().build();

    }

}

