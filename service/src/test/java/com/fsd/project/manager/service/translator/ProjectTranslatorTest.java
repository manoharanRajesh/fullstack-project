package com.fsd.project.manager.service.translator;

import com.fsd.project.manager.service.dao.data.Project;
import com.fsd.project.manager.service.dao.data.User;
import com.fsd.project.manager.service.service.translator.ProjectTranslator;
import com.fsd.project.manager.service.service.translator.UserTranslate;
import com.fsd.project.manager.service.view.ProjectModel;
import com.fsd.project.manager.service.view.UserModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.fsd.project.manager.service.TestUtils.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@DisplayName("Test translate of project document and projectModel.")
public class ProjectTranslatorTest {
    @Mock
    UserTranslate userTranslate;
    @InjectMocks
    ProjectTranslator translator;

    @Test
    @DisplayName("Should translate 2 Project Doc")
    public void test_translate_2_Proj_Doc() {
        Project p = translator.translate(createProjModel());

        assertAll("",
                () -> assertNull(p.getId()),
                () -> assertEquals(1, p.getPriority())
        );

    }

    @Test
    @DisplayName("Should translate 2 Project Model")
    public void test_translate_2_Proj_Model() {
        when(this.userTranslate.translate((User) any())).thenReturn(createUserModel());

        ProjectModel p = translator.translate(createProject("t", 1, 1));

        assertAll("",
                () -> assertEquals(1, p.getPriority())
        );

    }

    @Test
    @DisplayName("Should translate 2 Project Doc")
    public void test_translate_2_Proj_Doc1() {
        Project p = translator.translate(createProjModel(), createUser());

        assertAll("expect project for create flow.",
                () -> assertNull(p.getId()),
                () -> assertEquals(1, p.getPriority()),
                () -> assertNotNull(p.getManager())

        );

    }


    @Test
    @DisplayName("Should translate 2 Project Doc")
    public void test_update_translate_2_Proj_Doc1() {
        when(this.userTranslate.translate((UserModel) any())).thenReturn(createUser());

        Project p = translator.translate(createProjModel(), createProject("t", 1, 1));

        assertAll("expect project for create flow.",
                () -> assertEquals(1, p.getPriority()),
                () -> assertNotNull(p.getManager())

        );

    }

    @Test
    @DisplayName("Should translate 2 Project Doc")
    public void test_update_translate_2_Proj_Doc2() {


        Project p = translator.translate(createProjModel(), createUser(), createProject("t", 1, 1));

        assertAll("expect project for create flow.",
                () -> assertEquals(1, p.getPriority()),
                () -> assertNotNull(p.getManager())

        );

    }
}
