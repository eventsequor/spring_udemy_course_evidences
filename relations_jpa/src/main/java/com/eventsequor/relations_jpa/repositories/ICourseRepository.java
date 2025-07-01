package com.eventsequor.relations_jpa.repositories;

import com.eventsequor.relations_jpa.entities.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ICourseRepository extends CrudRepository<Course, Long> {

    @Query("select c from Course c left join fetch c.students where c.id=?1")
    Optional<Course> findOneWithStudents(Long id);
}
