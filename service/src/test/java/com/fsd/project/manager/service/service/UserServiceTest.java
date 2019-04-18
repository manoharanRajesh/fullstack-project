package com.fsd.project.manager.service.service;

import com.fsd.project.manager.service.dao.UserRepository;
import com.fsd.project.manager.service.dao.data.User;
import com.fsd.project.manager.service.service.translator.UserTranslate;
import com.fsd.project.manager.service.view.UserModel;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static com.fsd.project.manager.service.TestUtils.createUser;
import static com.fsd.project.manager.service.TestUtils.createUserModel;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@DisplayName("GIVEN mock repo and translator")
public class UserServiceTest {

    @Mock
    UserTranslate userTranslate;
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService service;

    @Test
    @DisplayName("Then i should be able to get user from repo.")
    public void test_get() {
        List<User> users = Lists.newArrayList(createUser());
        when(this.userRepository.findAll()).thenReturn(users);
        when(this.userTranslate.translate((User) any())).thenReturn(createUserModel());
        this.service.get();
        verify(this.userRepository, times(1)).findAll();
        verify(this.userTranslate, times(1)).translate((User) any());
    }

    @Test
    @DisplayName("Then i should be able to get user from repo.")
    public void test_get_id() {
        when(this.userRepository.findById("1"))
                .thenReturn(Optional.of(createUser()));
        when(this.userTranslate.translate((User) any())).thenReturn(createUserModel());
        this.service.get("1");
        verify(this.userRepository, times(1)).findById("1");
        verify(this.userTranslate, times(1)).translate((User) any());
        assertAll(
                () -> assertFalse(this.service.getUser(null).isPresent())
        );

    }

    @Test
    @DisplayName("Then i should be able to delete user from repo.")
    public void test_delete_id() {
        this.service.delete("1");
        verify(this.userRepository, times(1)).deleteById("1");
    }

    @Test
    @DisplayName("Then i should be able to create user from repo.")
    public void test_new() {
        when(this.userTranslate.translate((UserModel) any())).thenReturn(createUser());
        this.service.create(createUserModel());
        verify(this.userRepository, times(1)).save(any());
        verify(this.userTranslate, times(1)).translate((UserModel) any());
    }

    @Test
    @DisplayName("Then i should be able to update user from repo.")
    public void test_change_user() {
        when(this.userRepository.findById(any())).thenReturn(Optional.of(createUser()));
        when(this.userTranslate.translate((UserModel) any(), (User) any())).thenReturn(createUser());
        this.service.update(createUserModel());
        verify(this.userRepository, times(1)).save(any());
        verify(this.userTranslate, times(1)).translate((UserModel) any(), (User) any());
    }
    @Test
    @DisplayName("Then for empty id never search in DB")
    public void test_new_getFromDb() {
        assertNull(this.service.get(""));
        verify(this.userTranslate,never()).translate((User)any());

    }
}
