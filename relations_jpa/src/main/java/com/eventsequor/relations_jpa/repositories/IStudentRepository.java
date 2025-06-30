package com.eventsequor.relations_jpa.repositories;

import com.eventsequor.relations_jpa.entities.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IStudentRepository extends CrudRepository<Student, Long> {

    @Query("select s from Student s left join fetch s.courses where s.id=?1")
    Optional<Student> findOneWithCourses(Long id);
}
