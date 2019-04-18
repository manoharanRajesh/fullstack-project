package com.fsd.project.manager.service.dao.data;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private long employId;
    private String firstName;
    private String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {

    }

    @Override
    public String toString() {
        return String.format(
                "User[empId=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

}
