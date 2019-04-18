package com.fsd.project.manager.service.translator;

import com.fsd.project.manager.service.dao.data.Project;
import com.fsd.project.manager.service.dao.data.Task;
import com.fsd.project.manager.service.dao.data.User;
import com.fsd.project.manager.service.service.translator.ProjectTranslator;
import com.fsd.project.manager.service.service.translator.TaskTranslate;
import com.fsd.project.manager.service.service.translator.UserTranslate;
import com.fsd.project.manager.service.view.TaskModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.fsd.project.manager.service.TestUtils.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@DisplayName("Test translate of Task document and projectModel.")
public class TaskTranslateTest {

    @Mock
    ProjectTranslator projectTranslator;
    @Mock
    UserTranslate userTranslate;
    @InjectMocks
    TaskTranslate translator;

    @Test
    @DisplayName("Should translate 2 Task Doc")
    public void test_translate_2_Doc() {
        Task p = translator.translate(createTaskModel(),
                createProject("t", 1, 1), createUser());

        assertAll("expect task document",
                () -> assertEquals(createUser(), p.getUser()),
                () -> assertEquals(1, p.getPriority())
        );

    }


    @Test
    @DisplayName("Should translate 2 new Task Doc")
    public void test_translate_2_new_Doc() {
        Task p = translator.translate(createTaskModel(),
                createProject("t", 1, 1), createUser());

        assertAll("expect task document",
                () -> assertEquals("1", p.getId()),
                () -> assertEquals(1, p.getPriority())
        );

    }

    @Test
    @DisplayName(" should translate to Model")
    public void test_2_update_doc() {

        Task p = translator.translate(createTaskModel(),
                createTask("t"), createProject("t", 1, 1), createUser());

        assertAll("expect task document",
                () -> assertNotNull(p.getUser()),
                () -> assertNotNull(p.getProject())
        );
    }

    @Test
    @DisplayName(" should translate to Model")
    public void test_2_model() {
        when(this.projectTranslator.translate((Project) any()))
                .thenReturn(createProjModel());
        when(this.userTranslate.translate((User) any())).thenReturn(createUserModel());

        TaskModel p = translator.translate(createTask("t"));

        assertAll("expect task document",
                () -> assertNotNull(p.getUser()),
                () -> assertNotNull(p.getProject())
        );
    }
}
