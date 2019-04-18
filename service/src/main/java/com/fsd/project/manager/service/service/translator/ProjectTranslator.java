package com.fsd.project.manager.service.service.translator;

import com.fsd.project.manager.service.dao.data.Project;
import com.fsd.project.manager.service.dao.data.User;
import com.fsd.project.manager.service.view.ProjectModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectTranslator {
    @Autowired
    UserTranslate userTranslate;


    public Project translate(ProjectModel fromModel, User manager, Project toDoc) {
        toDoc.setTitle(fromModel.getTitle());
        toDoc.setPriority(fromModel.getPriority());
        toDoc.setStartDate(fromModel.getStartDate());
        toDoc.setEndDate(fromModel.getEndDate());
        toDoc.setManager(manager);
        return toDoc;
    }

    public Project translate(ProjectModel fromModel, Project toDoc) {
        toDoc.setTitle(fromModel.getTitle());
        toDoc.setPriority(fromModel.getPriority());
        toDoc.setStartDate(fromModel.getStartDate());
        toDoc.setEndDate(fromModel.getEndDate());
        toDoc.setManager(userTranslate.translate(fromModel.getManager()));
        return toDoc;
    }

    public Project translate(ProjectModel fromModel, User manager) {
        return new Project(manager, fromModel.getTitle(), fromModel.getPriority(),
                fromModel.getStartDate(), fromModel.getEndDate());
    }

    public Project translate(ProjectModel fromModel) {
        return new Project(userTranslate.translate(fromModel.getManager()), fromModel.getTitle(), fromModel.getPriority(),
                fromModel.getStartDate(), fromModel.getEndDate());
    }


    public ProjectModel translate(Project fromDoc) {
        return new ProjectModel(fromDoc.getId(),
                userTranslate.translate(fromDoc.getManager()),
                fromDoc.getTitle(), fromDoc.getPriority(),
                fromDoc.getStartDate(), fromDoc.getEndDate());
    }
}
