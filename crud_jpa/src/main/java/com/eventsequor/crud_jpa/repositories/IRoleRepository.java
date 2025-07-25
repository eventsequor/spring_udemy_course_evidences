package com.eventsequor.crud_jpa.repositories;

import com.eventsequor.crud_jpa.entities.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IRoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
