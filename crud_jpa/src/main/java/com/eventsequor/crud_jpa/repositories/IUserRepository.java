package com.eventsequor.crud_jpa.repositories;

import com.eventsequor.crud_jpa.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<User, Long> {
}
