package com.fsd.project.manager.service;
//remove::start[]

import com.fsd.project.manager.service.dao.ProjectRepository;
import com.fsd.project.manager.service.dao.TaskRepository;
import com.fsd.project.manager.service.dao.UserRepository;
import com.fsd.project.manager.service.dao.data.Project;
import com.fsd.project.manager.service.dao.data.Task;
import com.fsd.project.manager.service.dao.data.User;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.specification.RequestSpecification;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

//remove::end[]
//remove::start[]


//remove::end[]

@ExtendWith(MockitoExtension.class)
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
//@SpringBootTest(classes = ServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@DataMongoTest
@SpringBootTest(classes = ServiceApplication.class)
@ActiveProfiles("it")
@AutoConfigureRestDocs(outputDir = "target/snippets")
public abstract class ProjectManagerBase {
    private static final String OUTPUT = "target/generated-snippets";
    private RequestSpecification spec;

    /* @Rule
     public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation(OUTPUT);

     @Rule
     public TestName testName = new TestName();*/
    private MockMvc mockMvc;


    @Autowired
    WebApplicationContext context;
    @Autowired
    UserRepository usrRepo;
    @Autowired
    ProjectRepository projectRepo;
    @Autowired
    TaskRepository tskRepo;
    private static final DateTimeFormatter dateFormater =
            DateTimeFormatter.ofPattern("dd-mm-yyyy");
    //@LocalServerPort
    // int port;

    private MockMvcRequestSpecification given;

    @BeforeEach
    public void setup(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        // RestAssured.baseURI = "http://localhost";
        // RestAssured.port = this.port;
        //RestAssuredMockMvc.webAppContextSetup(this.context);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(document("{method-name}/{step}/"))
                .build();
        RestAssuredMockMvc.mockMvc(this.mockMvc);
        usrRepo.deleteAll();
        usrRepo.save(createUser("fstName", "LstName", "1"));
        usrRepo.save(createUser("fstName1", "LstName1", "2"));
        usrRepo.save(createUser("fstName2", "LstName2", "3"));
        usrRepo.save(createUser("toDelete", "toDelete", "4"));
        usrRepo.save(createUser("manger", "testManager", "5"));
        usrRepo.save(createUser("worker", "testWorker", "6"));

        projectRepo.save(createProject("title1", 1, 1, "1"));
        projectRepo.save(createProject("title2", 1, 1, "2"));
        projectRepo.save(createProject("title3", 1, 1, "3"));
        projectRepo.save(createProject("MultiTaskProject", 1, 1, "4"));

        tskRepo.save(createTask("task1", "1"));
        tskRepo.save(createTask("task2", "2"));
        tskRepo.save(createTask("task3", "3"));

    }

    private Project createProject(String title, int priority, int duration, String id) {
        Project p = new Project();
        p.setId(id);
        p.setManager(createUser("manger", "testManager", "5"));
        p.setTitle(title);
        p.setPriority(priority);
        p.setStartDate(LocalDateTime.now().format(dateFormater));
        p.setEndDate(LocalDateTime.now().plusMonths(duration).format(dateFormater));
        return p;
    }


    private User createUser(String fstName, String lstName, String id) {
        User usr = new User(fstName, lstName);
        usr.setId(id);
        return usr;
    }

    private Task createTask(String title, String id) {
        Task t = new Task();
        t.setId(id);
        t.setTitle(title);
        t.setPriority(1);
        t.setStartDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-mm-yyyy")));
        t.setEndDate(LocalDateTime.now().plusMonths(12).format(DateTimeFormatter.ofPattern("dd-mm-yyyy")));
        t.setProject(createProject("MultiTaskProject", 1, 1, "4"));
        t.setUser(createUser("worker", "testWorker", "6"));
        return t;
    }

}
