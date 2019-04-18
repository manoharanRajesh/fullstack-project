package com.fsd.project.manager.service.dao.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Tag("UnitTest")
public class ProjectTest {
    @Test
    @DisplayName("create Project.")
    public void create() {
        Project default_Project = new Project();

        assertAll("Create Project Document.",
                () -> {
                    assertEquals("Project(id=null, manager=null, title=null, priority=0, startDate=null, endDate=null)"
                            , default_Project.toString(),
                            "wrong with default User Document.");
                }
        );
    }

    @Test
    @DisplayName("create Project.")
    public void equals() {
        Project p1 = new Project();
        Project p2 = new Project();
        Project p3 = null;

        assertAll("Equals ",
                () -> assertEquals(p1,p2),
                () -> assertNotEquals(p1,p3),
                () -> assertEquals(p1,p1)
        );

        assertAll("hash ",
                () -> assertEquals(p1.hashCode(),p1.hashCode()),
                () -> assertEquals(p1.hashCode(),p2.hashCode())
        );
    }





}
