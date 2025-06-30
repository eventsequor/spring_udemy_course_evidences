package com.eventsequor.relations_jpa.repositories;

import com.eventsequor.relations_jpa.entities.Course;
import org.springframework.data.repository.CrudRepository;

public interface ICourseRepository extends CrudRepository<Course, Long> {
}
