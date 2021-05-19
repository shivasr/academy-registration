package com.app.academyregistration.services;

import com.app.academyregistration.models.Student;
import com.app.academyregistration.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service to manage students.
 */
@Service
public class StudentService {

    // CRUD repository implementation
    private StudentRepository studentRepository;

    /**
     * Constructor.
     *
     * @param studentRepository Repository implementation to save to database.
     */
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Create or Update a student.
     *
     * @param student Student details to create or update.
     *
     * @return Updated student details
     */
    public Student createOrUpdateStudent(Student student) {
        return studentRepository.save(student);
    }

    /**
     * List all students.
     *
     * @return list of existing students.
     */
    public List<Student> listStudents() {
        return studentRepository.findAll();
    }

    /**
     * Select a particular student by id
     *
     * @param id student Id
     * @return student identified by id
     */
    public Optional<Student> selectAStudentById(long id) {
        return studentRepository.findById(id);
    }

    public List<Student> listAllStudentsById(List<Long> students) {
        return studentRepository.findAllById(students);
    }
}
