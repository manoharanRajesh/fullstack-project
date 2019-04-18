package com.fsd.project.manager.service.dao.data;

import lombok.Lombok;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.annotation.Nonnull;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TaskTest {
    @Test
    @DisplayName("create Task.")
    public void create() {
        Task default_Project = new Task();

        assertAll("Create Task Document.",
            () ->  {
                assertEquals("Task(id=null, parent=null, user=null, project=null, title=null, priority=0, startDate=null, endDate=null)"
                        ,default_Project.toString(),
                        "wrong with default User Document.");
            }
        );
    }


    @Test
    @DisplayName("Test equals and hash")
    public void test_equals_hash() {
        Task t1 = new Task();
        Task t2 = new Task();
        Task t3 = null;
        Task t4 = new Task();
        t4.setParent(t1);

        assertAll("equals",
                () -> assertEquals(t1,t2),
                () -> assertEquals(t1,t1),
                () -> assertNotEquals(t1,t3),
                () -> assertNotEquals(t1,t4)

        );

        assertAll("hash",
                () -> assertEquals(t1.hashCode(),t1.hashCode()),
                () -> assertEquals(t1.hashCode(),t2.hashCode()),
                () -> assertNotEquals(t1.hashCode(),t4.hashCode())

        );



    }


}
