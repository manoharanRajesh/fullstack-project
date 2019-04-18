package com.fsd.project.manager.service.service;

import com.fsd.project.manager.service.dao.TaskRepository;
import com.fsd.project.manager.service.dao.data.Project;
import com.fsd.project.manager.service.dao.data.Task;
import com.fsd.project.manager.service.dao.data.User;
import com.fsd.project.manager.service.service.translator.TaskTranslate;
import com.fsd.project.manager.service.view.TaskModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class TaskService {

    @Autowired
    TaskTranslate taskTranslate;
    @Autowired
    TaskRepository taskRepo;
    @Autowired
    UserService userService;
    @Autowired
    ProjectService projectService;

    public List<TaskModel> get() {
        return taskRepo.findAll()
                .stream()
                .map(t -> taskTranslate.translate(t))
                .collect(Collectors.toList());

    }

    public TaskModel get(String id) {
        Optional<Task> task = getTask(id);
        if (task.isPresent()) {
            return this.taskTranslate.translate(task.get());
        }
        return null;
    }

    @Transactional
    public void create(TaskModel fromModel) {

        User user = getUser(fromModel);
        Project project = getProject(fromModel);
        taskRepo.save(this.taskTranslate.translate(fromModel, project, user));
    }

    private Project getProject(TaskModel fromModel) {
        Optional<Project> optProject = this.projectService.getFromDb(fromModel.getProject().getId());
        return optProject.isPresent() ? optProject.get() : null;
    }

    @Transactional
    public void update(TaskModel fromModel) {
        User user = getUser(fromModel);
        Project project = getProject(fromModel);
        Optional<Task> task = getTask(fromModel.getId());
        if (task.isPresent()) {
            taskRepo.save(this.taskTranslate.translate(fromModel, task.get(), project, user));
        } else {
            taskRepo.save(this.taskTranslate.translate(fromModel, project, user));
        }
    }

    private User getUser(TaskModel fromModel) {
        Optional<User> optUser = this.userService.getUser(fromModel.getUser().getEmpId());
        return optUser.isPresent() ? optUser.get() : null;
    }

    @Transactional
    public void delete(String id) {
        taskRepo.deleteById(id);

    }

    private Optional<Task> getTask(String id) {
        if (StringUtils.isNotBlank(id)) {
            return taskRepo.findById(id);
        }
        return Optional.empty();
    }

}
