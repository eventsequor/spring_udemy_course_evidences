package com.eventsequor.spring_errors.services;

import com.eventsequor.spring_errors.exceptions.UserNotFoundException;
import com.eventsequor.spring_errors.models.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private List<User> users;

    @Override
    public Optional<User> findById(long id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return users;
    }
}
