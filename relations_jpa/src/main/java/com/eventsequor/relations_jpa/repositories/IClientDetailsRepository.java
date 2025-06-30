package com.eventsequor.relations_jpa.repositories;

import com.eventsequor.relations_jpa.entities.ClientDetails;
import org.springframework.data.repository.CrudRepository;

public interface IClientDetailsRepository extends CrudRepository<ClientDetails, Long> {
}
