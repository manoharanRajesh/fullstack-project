package com.fsd.project.manager.service.dao;

import com.fsd.project.manager.service.dao.data.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    public User findByFirstName(String firstName);

    public User findByLastName(String lastName);

    public void deleteByFirstName(String firstName);

    public void deleteByLastName(String firstName);
}
