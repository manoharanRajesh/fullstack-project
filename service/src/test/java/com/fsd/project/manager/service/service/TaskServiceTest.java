package com.fsd.project.manager.service.service;

import com.fsd.project.manager.service.dao.TaskRepository;
import com.fsd.project.manager.service.dao.UserRepository;
import com.fsd.project.manager.service.dao.data.Task;
import com.fsd.project.manager.service.service.translator.TaskTranslate;
import com.fsd.project.manager.service.view.TaskModel;
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

import static com.fsd.project.manager.service.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@DisplayName("GIVEN mock repo and service")
public class TaskServiceTest {

    @Mock
    TaskTranslate translate;
    @Mock
    TaskRepository repository;
    @Mock
    UserRepository userRepository;
    @Mock
    UserService userService;
    @Mock
    ProjectService projectService;
    @InjectMocks
    TaskService service;

    @Test
    @DisplayName("Then i should get all task.")
    public void test_get_task() {
        List<Task> tsks = Lists.newArrayList(createTask("task"));
        when(this.repository.findAll()).thenReturn(tsks);
        when(this.translate.translate(any())).thenReturn(createTaskModel());

        List lst = this.service.get();

        assertAll("",
                () -> {
                    assertTrue(lst.size() == 1);
                });
        verify(this.repository, times(1)).findAll();

    }

    @Test
    @DisplayName("Then i should get task of the given id. ")
    public void test_get_by_id() {

        when(this.repository.findById("1"))
                .thenReturn(Optional.of(createTask("task")));
        when(this.translate.translate(any())).thenReturn(createTaskModel());

        TaskModel t = this.service.get("1");

        assertAll("expect taskModel",
                () -> {
                    assertEquals(t, createTaskModel());
                });

        TaskModel t1 = this.service.get("");

        assertAll("expect must be null",
                () -> assertNull(t1));


    }

    @Test
    @DisplayName("should create")
    public void test_create() {

        when(this.userService.getUser(any())).thenReturn(Optional.of(createUser()));
        when(this.projectService.getFromDb(any())).thenReturn(Optional.of(createProject("p", 1, 1)));
        when(this.translate.translate(any(), any(), any()))
                .thenReturn(createTask("task"));

        this.service.create(createTaskModel(
                createUserModel(), createProjModel()));

        verify(this.repository, times(1)).save(any());

    }

    @Test
    @DisplayName("should update")
    public void test_update() {

        when(this.userService.getUser(any())).thenReturn(Optional.of(createUser()));
        when(this.projectService.getFromDb(any())).thenReturn(Optional.of(createProject("p", 1, 1)));
        when(this.translate.translate(any(), any(), any()))
                .thenReturn(createTask("task"));

        this.service.update(createTaskModel(
                createUserModel(), createProjModel()));

        verify(this.repository, times(1)).save(any());

    }
    @Test
    @DisplayName("should update")
    public void test_update_no_user() {

        when(this.userService.getUser(any())).thenReturn(Optional.empty());
        when(this.projectService.getFromDb(any())).thenReturn(Optional.empty());
        when(this.translate.translate(any(), any(), any()))
                .thenReturn(createTask("task"));

        this.service.update(createTaskModel(
                createUserModel(), createProjModel()));

        verify(this.repository, times(1)).save(any());

    }

   @Test
    @DisplayName("should update")
    public void test_update1() {
        when(this.repository.findById(any()))
                .thenReturn(Optional.of(createTask("t")));
        when(this.userService.getUser(any())).thenReturn(Optional.of(createUser()));
        when(this.projectService.getFromDb(any())).thenReturn(Optional.of(createProject("p", 1, 1)));

        when(this.translate.translate(any(), any(), any(),any()))
                .thenReturn(createTask("task"));

        this.service.update(createTaskModel(
                createUserModel(), createProjModel()));

        verify(this.repository, times(1)).save(any());

    }


    @Test
    @DisplayName("Should delete the task.")
    public void test_deleteByID() {
        this.service.delete("1");
        verify(this.repository, times(1)).deleteById("1");

    }


}
