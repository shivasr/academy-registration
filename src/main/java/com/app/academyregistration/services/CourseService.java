package com.app.academyregistration.services;

import com.app.academyregistration.models.Course;
import com.app.academyregistration.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service to manage courses.
 */
@Service
public class CourseService {

    // CRUD repository implementation
    private CourseRepository courseRepository;

    /**
     * Constructor.
     *
     * @param courseRepository Repository implementation to save to database.
     */
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /**
     * Create or Update a course.
     *
     * @param course Course details to create or update.
     *
     * @return Updated course
     */
    public Course createOrUpdateCourse(Course course) {
        return courseRepository.save(course);
    }

    /**
     * List all courses.
     *
     * @return list of existing courses.
     */
    public List<Course> listCourses() {
        return courseRepository.findAll();
    }

    /**
     * Select a particular course by id
     *
     * @param id course Id
     * @return course identified by id
     */
    public Optional<Course> selectACourseById(long id) {
        return courseRepository.findById(id);
    }
}
