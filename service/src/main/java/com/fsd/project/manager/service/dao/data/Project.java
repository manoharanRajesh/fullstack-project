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
@Document(collection = "projects")
public class Project {
    @Id
    private String id;
    @DBRef
    private User manager;
    private String title;
    private int priority;
    private String startDate;
    private String endDate;

    public Project(User manager, String title, int priority, String startDate, String endDate) {
        this.manager = manager;
        this.title = title;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Project() {
    }
}
