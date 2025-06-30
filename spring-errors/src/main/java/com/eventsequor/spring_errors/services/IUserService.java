package com.eventsequor.spring_errors.services;

import com.eventsequor.spring_errors.models.domain.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    public Optional<User> findById(long id);
    public List<User> findAll();

}
