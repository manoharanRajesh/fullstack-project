package com.fsd.project.manager.service.service;

import com.fsd.project.manager.service.dao.ProjectRepository;
import com.fsd.project.manager.service.dao.data.Project;
import com.fsd.project.manager.service.dao.data.User;
import com.fsd.project.manager.service.service.translator.ProjectTranslator;
import com.fsd.project.manager.service.view.ProjectModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjectService {

    @Autowired
    ProjectTranslator translator;
    @Autowired
    ProjectRepository projectRepo;
    @Autowired
    UserService userService;

    public List<ProjectModel> get() {

        return projectRepo.findAll()
                .stream()
                .map(p -> translator.translate(p))
                .collect(Collectors.toList());

    }

    public ProjectModel get(String id) {
        Optional<Project> usrDoc = getFromDb(id);
        if (usrDoc.isPresent()) {
            return this.translator.translate(usrDoc.get());
        }
        return null;
    }

    @Transactional
    public void create(ProjectModel fromModel) {
        Optional<User> optionalUser = this.userService.getUser(fromModel.getManager().getEmpId());
        User manager = optionalUser.isPresent() ? optionalUser.get() : null;
        projectRepo.save(this.translator.translate(fromModel, manager));
    }

    @Transactional
    public void update(ProjectModel fromModel) {
        Optional<Project> usrDoc = getFromDb(fromModel.getId());
        Optional<User> optionalUser = this.userService.getUser(fromModel.getManager().getEmpId());
        User manager = optionalUser.isPresent() ? optionalUser.get() : null;
        if (usrDoc.isPresent()) {
            Project p = this.translator.translate(fromModel, manager, usrDoc.get());
            projectRepo.save(p);
        } else {
            projectRepo.save(this.translator.translate(fromModel, manager));
        }
    }

    @Transactional
    public void delete(String id) {
        projectRepo.deleteById(id);

    }

    public Optional<Project> getFromDb(String id) {
        if (StringUtils.isNotBlank(id)) {
            return projectRepo.findById(id);
        }
        return Optional.empty();

    }

}
