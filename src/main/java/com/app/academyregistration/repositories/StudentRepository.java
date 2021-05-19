package com.app.academyregistration.repositories;

import com.app.academyregistration.models.Professor;
import com.app.academyregistration.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Interface for course
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
