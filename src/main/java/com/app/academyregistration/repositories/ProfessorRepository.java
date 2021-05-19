package com.app.academyregistration.repositories;

import com.app.academyregistration.models.Course;
import com.app.academyregistration.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Interface for course
 */
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
