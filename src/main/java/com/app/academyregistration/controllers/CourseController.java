package com.app.academyregistration.controllers;

import com.app.academyregistration.exceptions.ApplicationException;
import com.app.academyregistration.models.*;
import com.app.academyregistration.services.CourseService;
import com.app.academyregistration.services.ProfessorService;
import com.app.academyregistration.services.StudentService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseController {

    // Student Service to manage students data
    private final StudentService studentService;

    // Course Service to manage course data
    private final CourseService courseService;

    // Professor Service to mange professors data
    private final ProfessorService professorService;

    /**
     * Constructor
     *
     * @param courseService  Course Service Implementation
     * @param studentService Students Service Implementation
     * @param professorService Professor Serivce Implementation
     */
    public CourseController(CourseService courseService, StudentService studentService, ProfessorService professorService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.professorService = professorService;
    }

    /**
     * List all courses.
     *
     * @return List of courses
     */
    @GetMapping
    public @ResponseBody
    ResponseEntity<List<Course>> listCourses() {
        List<Course> courses = courseService.listCourses();
        return ResponseEntity.ok(courses);
    }

    /**
     * Create course
     *
     * @param course Course to be created
     * @return returns created course
     */
    @PostMapping
    public @ResponseBody
    ResponseEntity<Course> createCourse(@RequestBody Course course) {
       Course savedCourse = courseService.createOrUpdateCourse(course);
       return ResponseEntity.ok(savedCourse);
    }

    /**
     * Retrieve a course.
     *
     * @return List of courses
     */
    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<Course> listCourses(@PathVariable("id") long id) {
        Course course = courseService.selectACourseById(id).orElseThrow(() ->
                new ApplicationException(String.format("Unknown course id: %d", id)));

        return ResponseEntity.ok(course);
    }

    /**
     * Assign one or more professors to the course.
     *
     * @param request Request to assign studets/professors
     * @return returns Course or Application error
     */
    @PatchMapping("/{courseId}")
    public @ResponseBody
    ResponseEntity<Object> assignStudentsAndProfessors(@RequestBody CourseUpdateOnly request, @PathVariable("courseId") Long id) {
        Course savedCourse = courseService.selectACourseById(id).orElseThrow(() ->
                new ApplicationException(String.format("Unknown course id: %o", id)));

        if(Optional.ofNullable(request.getProfessors()).isPresent() && !request.getProfessors().isEmpty()) {
            // Get list of professor entities to assign
            List<Professor> professors = professorService.listAllProfessorsById(request.getProfessors());

            if(professors.size() != request.getProfessors().size()) {
                request.getProfessors().removeAll(professors.stream().map(Professor::getId).collect(Collectors.toList()));
                return generateError(1001L, String.format("There some invalid professor Ids in the list: %s. Please review.", request.getProfessors()));
            }

            // Set the professors and students to course
            savedCourse.setProfessors(professors);
        }

        if(Optional.ofNullable(request.getStudents()).isPresent() && !request.getStudents().isEmpty()) {
            // Get list of student entities to assign
            List<Student> students = studentService.listAllStudentsById(request.getStudents());

            if(students.size() != request.getStudents().size()) {
                request.getStudents().removeAll(students.stream().map(Student::getId).collect(Collectors.toList()));
                return generateError(1001L, String.format("There some invalid student Ids in the list: %s. Please review.", request.getStudents()));
            }

            // Set the professors and students to course
            savedCourse.setStudents(students);
        }

        // Save the course details.
        savedCourse = courseService.createOrUpdateCourse(savedCourse);
        return ResponseEntity.ok(savedCourse);
    }

    @NotNull
    private ResponseEntity<Object> generateError(long errorCode, String errorMessage) {
        ApplicationError error = ApplicationError.builder()
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
