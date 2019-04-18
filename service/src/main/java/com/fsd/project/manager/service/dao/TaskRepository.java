package com.fsd.project.manager.service.dao;

import com.fsd.project.manager.service.dao.data.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
}
