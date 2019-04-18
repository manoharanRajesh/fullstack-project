package com.fsd.project.manager.service.service;

import com.fsd.project.manager.service.dao.ProjectRepository;
import com.fsd.project.manager.service.dao.data.Project;
import com.fsd.project.manager.service.dao.data.User;
import com.fsd.project.manager.service.service.translator.ProjectTranslator;
import com.fsd.project.manager.service.view.ProjectModel;
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

import static com.fsd.project.manager.service.TestUtils.createProjModel;
import static com.fsd.project.manager.service.TestUtils.createProject;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//squid:S2187 (squid:S2187)
// Bug in sonar with Junit 5 https://jira.sonarsource.com/browse/SONARJAVA-2390
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@DisplayName("GIVEN mock repo and translator")
public class ProjectServiceTest {
    @Mock
    ProjectTranslator translator;
    @Mock
    ProjectRepository projectRepository;
    @Mock
    UserService userService;
    @InjectMocks
    ProjectService projectService;


    @Test
    @DisplayName("THEN should get valid reply ")
    public void test_get_all() {
        Project p = createProject("ci/cd setup", 3, 2);
        List<Project> projects = Lists.newArrayList(p);

        when(this.projectRepository.findAll()).thenReturn(projects);
        when(this.translator.translate(p)).thenReturn(createProjModel());

        List lst = this.projectService.get();
        assertAll(
                () -> assertTrue(!lst.isEmpty()),
                () -> assertEquals(lst.get(0), createProjModel())
        );

    }


    @Test
    @DisplayName("And id of task is given THEN should get respective Project. ")
    public void test_get_task() {
        Project p = createProject("ci/cd setup", 3, 2);
        List<Project> projects = Lists.newArrayList(p);

        when(this.projectRepository.findById("1")).thenReturn(Optional.of(p));
        when(this.translator.translate(p)).thenReturn(createProjModel());

        ProjectModel pm = this.projectService.get("1");
        assertAll(
                () -> assertEquals(pm, createProjModel())
        );
        ProjectModel pm1 = this.projectService.get("2");
        assertAll(
                () -> assertNull(pm1)
        );
    }

    @Test
    @DisplayName("Then i should be able to delete Project")
    public void test_delete() {
        this.projectService.delete("1");
        verify(this.projectRepository, times(1)).deleteById("1");
    }

    @Test
    @DisplayName("Then i should be able to update Project details.")
    public void test_update() {
        ProjectModel p = createProjModel();
        when(this.projectRepository.findById(any()))
                .thenReturn(Optional.of(createProject("t",1,1)));
        when(this.userService.getUser(any())).thenReturn(Optional.of(new User()));
        this.projectService.update(p);
        verify(this.userService, times(1)).getUser(any());
        verify(this.projectRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Then i should be able to update Project details.")
    public void test_update1() {
        ProjectModel p = createProjModel();
        when(this.userService.getUser(any())).thenReturn(Optional.of(new User()));
        this.projectService.update(p);
        verify(this.userService, times(1)).getUser(any());
        verify(this.projectRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Then i should be able to update Project details.")
    public void test_update_no_manager() {
        ProjectModel p = createProjModel();
        when(this.userService.getUser(any())).thenReturn(Optional.empty());
        this.projectService.update(p);
        verify(this.userService, times(1)).getUser(any());
        verify(this.projectRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Then i should be able to add Projects.")
    public void test_create() {
        ProjectModel p = createProjModel();
        when(this.userService.getUser(any())).thenReturn(Optional.of(new User()));
        this.projectService.create(p);
        verify(this.projectRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Then i should be able to add Projects.")
    public void test_create_no_manager() {
        ProjectModel p = createProjModel();
        when(this.userService.getUser(any())).thenReturn(Optional.empty());
        this.projectService.create(p);
        verify(this.projectRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Then for empty id never search in DB")
    public void test_new_getFromDb() {
        this.projectService.getFromDb("");
        verify(this.projectRepository,never()).findById(anyString());

    }
}
