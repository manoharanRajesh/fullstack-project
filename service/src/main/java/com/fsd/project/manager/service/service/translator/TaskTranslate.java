package com.fsd.project.manager.service.service.translator;

import com.fsd.project.manager.service.dao.data.Project;
import com.fsd.project.manager.service.dao.data.Task;
import com.fsd.project.manager.service.dao.data.User;
import com.fsd.project.manager.service.view.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskTranslate {
    @Autowired
    ProjectTranslator projectTranslator;

    @Autowired
    UserTranslate userTranslate;

    public Task translate(TaskModel fromModel, Task toDoc, Project project, User user) {
        toDoc.setTitle(fromModel.getTitle());
        toDoc.setId(fromModel.getId());
        toDoc.setStartDate(fromModel.getStartDate());
        toDoc.setEndDate(fromModel.getEndDate());
        toDoc.setPriority(fromModel.getPriority());
        toDoc.setUser(user);
        toDoc.setProject(project);
        return toDoc;
    }

    public Task translate(TaskModel fromModel, Project project, User user) {
        Task task = new Task();
        task.setTitle(fromModel.getTitle());
        task.setId(fromModel.getId());
        task.setStartDate(fromModel.getStartDate());
        task.setEndDate(fromModel.getEndDate());
        task.setPriority(fromModel.getPriority());
        task.setUser(user);
        task.setProject(project);
        return task;
    }

    public TaskModel translate(Task fromDoc) {
        return new TaskModel(fromDoc.getId(),
                null,
                this.userTranslate.translate(fromDoc.getUser()),
                this.projectTranslator.translate(fromDoc.getProject()),
                fromDoc.getTitle(),
                fromDoc.getPriority(),
                fromDoc.getStartDate(),
                fromDoc.getEndDate()
        );
    }

}
