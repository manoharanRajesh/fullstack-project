package com.fsd.project.manager.service.translator;

import com.fsd.project.manager.service.dao.data.User;
import com.fsd.project.manager.service.service.translator.UserTranslate;
import com.fsd.project.manager.service.view.UserModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.fsd.project.manager.service.TestUtils.createModel;
import static com.fsd.project.manager.service.TestUtils.createUser;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@DisplayName("Test translater of user and usermodel.")
public class UserTranslateTest {

    @InjectMocks
    UserTranslate translate;


    @Test
    @DisplayName("Should Translate model 2 user document.")
    public void test_Model_2_Doc() {

        User u = translate.translate(createModel());
        assertAll("expect correct first , last name and id",
                () -> assertEquals("firstName", u.getFirstName()),
                () -> assertEquals("LastName", u.getLastName()),
                () -> assertNull(u.getId())
        );
    }


    @Test
    @DisplayName("Should Translate model 2 user document.")
    public void test_update_Model_2_Doc() {

        User u = translate.translate(createModel(), createUser());
        assertAll("expect correct first , last name and id",
                () -> assertEquals("firstName", u.getFirstName()),
                () -> assertEquals("LastName", u.getLastName()),
                () -> assertEquals("id", u.getId())
        );
    }

    @Test
    @DisplayName("Should Translate model 2 user document.")
    public void test_doc_2_Model() {

        UserModel u = translate.translate(createUser());
        assertAll("expect correct first , last name and id",
                () -> assertEquals("FName", u.getFirstName()),
                () -> assertEquals("lName", u.getLastName()),
                () -> assertEquals("id", u.getEmpId())
        );
    }

}
