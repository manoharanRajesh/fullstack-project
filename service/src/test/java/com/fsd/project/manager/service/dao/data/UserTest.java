package com.fsd.project.manager.service.dao.data;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
@Tag("UnitTest")
public class UserTest {
    @Test
    @DisplayName("create user.")
    public void create() {
        User default_user = new User();
        User user = new User("firstName", "lastName");
        assertAll("Create User Document.",
                () -> {
                    assertEquals("User[empId=null, firstName='null', lastName='null']"
                            , default_user.toString(),
                            "wrong with default User Document.");
                },
                () -> {
                    assertEquals("User[empId=null, firstName='firstName', lastName='lastName']"
                            , user.toString(),
                            "validate toString.");
                }
        );
    }


    @Test
    @DisplayName("Test equals and hash")
    public void test_equals_hash() {
        User t1 = new User();
        User t2 = new User();
        User t3 = null;
        User t4 = new User();
        t4.setId("4");

        assertAll("equals",
                () -> assertEquals(t1,t2),
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
