package com.fsd.project.manager.service.dao.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@EqualsAndHashCode
@Data
@Document(collection = "tasks")
public class Task {
    @Id
    private String id;
    @DBRef
    private Task parent;
    @DBRef
    private User user;
    @DBRef
    private Project project;
    private @Getter
    @Setter
    String title;
    private int priority;
    private String startDate;
    private String endDate;

}
