package com.fsd.project.manager.service.service.translator;

import com.fsd.project.manager.service.dao.data.User;
import com.fsd.project.manager.service.view.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserTranslate {

    public User translate(UserModel fromModel, User toDoc) {
        toDoc.setFirstName(fromModel.getFirstName());
        toDoc.setLastName(fromModel.getLastName());
        return toDoc;
    }

    public User translate(UserModel fromModel) {
        return new User(fromModel.getFirstName(), fromModel.getLastName());
    }

    public UserModel translate(User fromDoc) {
        if(fromDoc!=null){
            return new UserModel(fromDoc.getId(), fromDoc.getFirstName(), fromDoc.getLastName());
        }
        return null;

    }
}
