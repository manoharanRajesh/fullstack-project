package com.fsd.project.manager.service;

import com.fsd.project.manager.service.dao.data.Project;
import com.fsd.project.manager.service.dao.data.Task;
import com.fsd.project.manager.service.dao.data.User;
import com.fsd.project.manager.service.view.ProjectModel;
import com.fsd.project.manager.service.view.TaskModel;
import com.fsd.project.manager.service.view.UserModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestUtils {
    public static final DateTimeFormatter dateFormater =
            DateTimeFormatter.ofPattern("dd-mm-yyyy");

    public static Project createProject(String title, int priority, int duration) {
        Project p = new Project();
        p.setTitle(title);
        //p.setId("1");
        p.setPriority(priority);
        p.setStartDate(LocalDateTime.now().format(dateFormater));
        p.setEndDate(LocalDateTime.now().plusMonths(duration).format(dateFormater));
        return p;
    }


    public static ProjectModel createProjModel() {

        return new ProjectModel("1", createModel(), null,
                1, null, null);
    }

    public static ProjectModel createProjModel(UserModel usr) {

        return new ProjectModel("1", usr, null,
                1, null, null);
    }

    public static UserModel createModel() {
        return new UserModel("1",
                "firstName", "LastName");
    }

    public static User createUser() {
        User u = new User("FName", "lName");
        u.setId("id");
        return u;
    }

    public static Task createTask(String title) {
        Task t = new Task();
        t.setTitle(title);
        return t;
    }

    public static Task createTask(String title, User usr, Project proj) {
        Task t = new Task();
        t.setTitle(title);
        t.setUser(usr);
        t.setProject(proj);
        return t;
    }

    public static TaskModel createTaskModel() {
        return new TaskModel("1", null,
                null, null,  "title"
                , 1, null, null);
    }

    public static TaskModel createTaskModel(UserModel u, ProjectModel p) {
        return new TaskModel("1", null,
                u, p,  "title"
                , 1, null, null);
    }

    public static UserModel createUserModel() {
        return new UserModel("id", "fname", "Lname");
    }

}
