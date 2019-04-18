package com.fsd.project.manager.service.dao;

import com.fsd.project.manager.service.ServiceApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.fsd.project.manager.service.TestUtils.createTask;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ServiceApplication.class)
@DataMongoTest
@DisplayName("GIVEN database mongodb with user repo.")
public class TaskRepositoryTest {

    @Autowired
    TaskRepository tskRepo;

    @BeforeEach
    public void setup() {
        tskRepo.deleteAll();
        tskRepo.save(createTask("Title 1"));
        tskRepo.save(createTask("Title 2"));
        tskRepo.save(createTask("Title 3"));
    }


    @Test
    @DisplayName("WHEN 2 task details are present. THEN return 2 Task.")
    public void fetch_task() {
        // Test get by filter
        assertEquals(3, tskRepo.findAll().size(), "Error in getting all tasks.");
    }

    @Test
    @DisplayName("WHEN task is empty. THEN return empty array.")
    public void fetch_task_empty() {
        tskRepo.deleteAll();
        // Test get by filter
        assertTrue(tskRepo.findAll().isEmpty(), "Error in getting all tasks.");
    }


}



