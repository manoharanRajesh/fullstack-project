package com.fsd.project.manager.service.service;

import com.fsd.project.manager.service.dao.UserRepository;
import com.fsd.project.manager.service.dao.data.User;
import com.fsd.project.manager.service.service.translator.UserTranslate;
import com.fsd.project.manager.service.view.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserTranslate translate;
    @Autowired
    UserRepository usrRepo;

    public List<UserModel> get() {
        return usrRepo.findAll()
                .stream()
                .map(u -> translate.translate(u))
                .filter(u -> StringUtils.isNotBlank(u.getLastName()) && StringUtils.isNotBlank(u.getEmpId()))
                .collect(Collectors.toList());

    }

    public UserModel get(String id) {
        Optional<User> usr = getUser(id);
        if (usr.isPresent()) {
            return translate.translate(usr.get());
        }
        return null;
    }


    @Transactional
    public void create(UserModel fromModel) {
        usrRepo.save(translate.translate(fromModel));
    }

    @Transactional
    public void update(UserModel fromModel) {
        Optional<User> usr = getUser(fromModel.getEmpId());
        if (usr.isPresent()) {
            usrRepo.save(translate.translate(fromModel, usr.get()));
        } else {
            usrRepo.save(translate.translate(fromModel));
        }
    }

    @Transactional
    public void delete(String id) {
        usrRepo.deleteById(id);

    }

    public Optional<User> getUser(String id) {
        if (StringUtils.isNotBlank(id)) {
            return usrRepo.findById(id);
        }
        return Optional.empty();
    }


}
