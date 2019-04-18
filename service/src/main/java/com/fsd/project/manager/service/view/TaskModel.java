package com.fsd.project.manager.service.view;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@lombok.Generated
public class TaskModel {
    private String id;
    private TaskModel parent;
    private UserModel user;
    private ProjectModel project;
    private String title;
    private int priority;
    private String startDate;
    private String endDate;
}
