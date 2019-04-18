package com.fsd.project.manager.service.dao;

import com.fsd.project.manager.service.ServiceApplication;
import com.fsd.project.manager.service.dao.data.User;
import com.google.common.collect.Lists;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ServiceApplication.class)
@DataMongoTest
@DisplayName("GIVEN database mongodb with user repo.")
public class UserRepositoryTest {

    @Autowired
    UserRepository usrRepo;

    @BeforeEach
    public void setup() {
        usrRepo.deleteAll();
        usrRepo.save(createUser("fstName", "LstName"));
        usrRepo.save(createUser("fstName1", "LstName1"));
        usrRepo.save(createUser("fstName2", "LstName2"));
    }

    private User createUser(String fstName, String lstName) {
        return new User(fstName, lstName);
    }


    @Nested
    @DisplayName("WHEN user details are fetched")
    class fetch_user {
        @Test
        @DisplayName("THEN should return 3 user. ")
        public void fetch_all() {
            List<User> usrs = usrRepo.findAll();
            assertAll(
                    "Validate user fetch method.",
                    () -> {
                        assertEquals("fstName", usrs.get(0).getFirstName(), "Error in fetch user details 1");
                        assertEquals("fstName1", usrs.get(1).getFirstName(), "Error in fetch user details 2");
                        assertEquals("fstName2", usrs.get(2).getFirstName(), "Error in fetch user details 3");
                    },
                    () -> {
                        assertEquals(3, usrs.size(), "Incorrect number of users.");
                    }
            );
        }

        @Test
        @DisplayName("AND filtered by different fields THEN get correct user details.")
        public void fetch_by() {
            // Test get by filter
            User u = usrRepo.findByFirstName("fstName");
            assertEquals("fstName", u.getFirstName(), "error in findByFirstName.");
            assertEquals("LstName", usrRepo.findByLastName("LstName").getLastName(), "error in findByLastName.");
            assertEquals("fstName", usrRepo.findAllById(Lists.newArrayList(u.getId())).iterator().next().getFirstName(), "error in findAllById.");
        }
    }

    @Nested
    @DisplayName("WHEN user details need to be update")
    class update_user {
        @Test
        @DisplayName("And user is present already in database. THEN Update user details.")
        public void save() {
            User u = usrRepo.findByFirstName("fstName");
            // Test update
            u.setFirstName("firstName");
            usrRepo.save(u);
            assertEquals("firstName", usrRepo.findByFirstName("firstName").getFirstName(), "Save does not work");
        }

        @Test
        @DisplayName("And user is not present already in database. THEN add the user.")
        public void save_new_user() {
            usrRepo.save(createUser("fstName4", "fstName"));
            assertEquals("fstName4", usrRepo.findByFirstName("fstName4").getFirstName(), "Save does not work");
        }
    }


    @Nested
    @DisplayName("WHEN user delete is called")
    class delete_user {
        @BeforeEach
        public void setup() {
            usrRepo.deleteAll();
            usrRepo.save(createUser("fstName", "LstName"));
            usrRepo.save(createUser("fstName1", "LstName1"));
            usrRepo.save(createUser("fstName2", "LstName2"));
        }

        @Test
        @DisplayName("AND filter by deleteByFirstName AND user is present.THEN user is deleted.")
        public void delete_by_first_name() {
            //Test delete
            usrRepo.deleteByFirstName("fstName");
            assertAll("Error in deleteByFirstName",
                    () -> assertEquals(2, usrRepo.findAll().size()),
                    () -> assertNull(usrRepo.findByFirstName("fstName"))
            );
        }

        @Test
        @DisplayName("AND filter by deleteByLastName AND user is present.THEN user is deleted.")
        public void delete_by_last_name() {
            usrRepo.deleteByLastName("LstName1");
            assertAll("Error in deleteByFirstName",
                    () -> assertEquals(2, usrRepo.findAll().size()),
                    () -> assertNull(usrRepo.findByLastName("LstName1"))
            );
        }

        @Test
        @DisplayName("AND delete all .THEN user is empty.")
        public void delete_all() {
            usrRepo.deleteAll();
            assertTrue(usrRepo.findAll().isEmpty());
        }
    }


}
