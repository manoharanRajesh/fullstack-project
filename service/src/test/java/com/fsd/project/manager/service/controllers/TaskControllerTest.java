package com.fsd.project.manager.service.controllers;

import com.fsd.project.manager.service.ServiceApplication;
import com.fsd.project.manager.service.service.TaskService;
import com.fsd.project.manager.service.view.TaskModel;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ServiceApplication.class)
@DisplayName("GIVEN service which serve task services")
public class TaskControllerTest {
    @Mock
    TaskService serviceMock;

    @InjectMocks
    TaskController controller;

    @Test
    @DisplayName("test get tasks http method.")
    public void testGetTasks() {
        when(this.serviceMock.get()).thenReturn(Lists.newArrayList(createModel()));
        List<TaskModel> actual = this.controller.get();
        assertEquals("1", actual.get(0).getId());
        verify(this.serviceMock, times(1)).get();
    }

    @Test
    @DisplayName("test get task http method.")
    public void testGetTask() {
        when(this.serviceMock.get("1")).thenReturn(createModel());
        TaskModel actual = this.controller.get("1");
        assertEquals("1", actual.getId());
        verify(this.serviceMock, times(1)).get("1");
    }

    @Test
    @DisplayName("Test Post http  - add method ")
    public void testUpdate() {
        TaskModel p = createModel();
        this.controller.add(p);
        verify(this.serviceMock, times(1)).update(p);
    }

    @Test
    @DisplayName("Test Post http  - delete method ")
    public void testDelete() {
        TaskModel p = createModel();
        this.controller.delete("1");
        verify(this.serviceMock, times(1)).delete("1");
    }

    @Test
    @DisplayName("Test Post http  - delete method invalid id ")
    public void testDelete_Invalid_id() {
        TaskModel p = createModel();
        this.controller.delete(null);
        verify(this.serviceMock, never()).delete(any());
    }


    private TaskModel createModel() {

        return new TaskModel("1", null, null, null, "title"
                , 1, null, null);
    }

}
