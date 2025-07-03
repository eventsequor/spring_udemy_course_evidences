package com.eventsequor.crud_jpa.services;

import com.eventsequor.crud_jpa.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUserService  {

    List<User> findAll();

    User save(User user);
}
