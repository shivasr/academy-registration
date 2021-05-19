package com.app.academyregistration.repositories;

import com.app.academyregistration.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Interface for course
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
