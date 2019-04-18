package com.fsd.project.manager.service.view;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@lombok.Generated
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserModel {
    private String empId;
    private String firstName;
    private String lastName;
}
