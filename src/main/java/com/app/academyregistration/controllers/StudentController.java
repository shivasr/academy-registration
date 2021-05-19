package com.app.academyregistration.controllers;

import com.app.academyregistration.exceptions.ApplicationException;
import com.app.academyregistration.models.Student;
import com.app.academyregistration.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    /**
     * Constructor
     *
     * @param studentService service implementation to mangae services.
     */
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * List all students.
     *
     * @return List of students
     */
    @GetMapping
    public @ResponseBody
    ResponseEntity<List<Student>> listStudents() {
        List<Student> students = studentService.listStudents();
        return ResponseEntity.ok(students);
    }

    /**
     * Create student
     *
     * @param student Student to be created
     * @return returns response containing the created student
     */
    @PostMapping
    public @ResponseBody
    ResponseEntity<Student> createStudent(@RequestBody Student student) {
       Student savedStudent = studentService.createOrUpdateStudent(student);
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("/students/{id}")
                .buildAndExpand(savedStudent.getId());
        return ResponseEntity.created(uriComponents.toUri()).body(savedStudent);
    }

    /**
     * Retrieve a student.
     *
     * @return List of students
     */
    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<Student> listStudents(@PathVariable("id") long id) {
        Student student = studentService.selectAStudentById(id).orElseThrow(() ->
                new ApplicationException(String.format("Unknown Student id: %d", id)));

        return ResponseEntity.ok(student);
    }
}
