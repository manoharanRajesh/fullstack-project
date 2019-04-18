package com.fsd.project.manager.service.dao;

import com.fsd.project.manager.service.dao.data.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {
    public Project findByTitle(String title);

}
