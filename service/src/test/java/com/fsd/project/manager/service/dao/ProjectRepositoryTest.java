package com.fsd.project.manager.service.dao;

import com.fsd.project.manager.service.ServiceApplication;
import com.fsd.project.manager.service.dao.data.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.CollectionUtils;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.fsd.project.manager.service.TestUtils.createProject;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ServiceApplication.class)
@DataMongoTest
@DisplayName("GIVEN database mongodb with project repo.")
public class ProjectRepositoryTest {
    private static final DateTimeFormatter dateFormater =
            DateTimeFormatter.ofPattern("dd-mm-yyyy");

    @Autowired
    ProjectRepository projectRepo;

    @BeforeEach
    public void setup() {
        projectRepo.deleteAll();
        projectRepo.save(createProject("Task manager", 1, 5));
        projectRepo.save(createProject("Task manager ui", 2, 5));
        projectRepo.save(createProject("ci/cd setup", 3, 2));
    }


    @Nested
    @DisplayName("WHEN there is a Read operation ")
    class fetch {
        @Test
        @DisplayName("THEN should able get 3 project record. ")
        public void fetch_all() {
            List<Project> projects = projectRepo.findAll();

            assertEquals(3, projects.size());
        }

    }

    @Nested
    @DisplayName("WHEN there is a Delete operation ")
    class delete {
        @Test
        @DisplayName("THEN should able get delete project record. ")
        public void delete_all() {
            projectRepo.deleteAll();

            assertTrue(CollectionUtils.isEmpty(projectRepo.findAll()));
        }
    }

    @Nested
    @DisplayName("WHEN there is a need to find and update operation ")
    class find_update {
        @Test
        @DisplayName("THEN should able update that project record. ")
        public void fetch_and_update() {
            Project p = projectRepo.findByTitle("Task manager");
            //   p.setTitle("Final Task Manager");
            projectRepo.save(p);

            //   assertEquals("Final Task Manager",projectRepo.findByTitle("Final Task Manager").getTitle());
            assertNull(projectRepo.findByTitle("Task Manager"));
        }

    }


}
