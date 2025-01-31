package com.ms.user.services;

import com.ms.user.dtos.UserRecord;
import com.ms.user.models.UserModel;
import com.ms.user.producers.UserProducer;
import com.ms.user.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserProducer userProducer;

    public UserService(UserRepository userRepository, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    @Transactional
    public void saveUser(UserRecord userRecord) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userRecord, userModel);
        this.userRepository.save(userModel);
        userProducer.publishMessageEmail(userModel);
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }
}
