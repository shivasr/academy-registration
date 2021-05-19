package com.app.academyregistration.services;

import com.app.academyregistration.models.Professor;
import com.app.academyregistration.models.Professor;
import com.app.academyregistration.repositories.ProfessorRepository;
import com.app.academyregistration.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service to manage courses.
 */
@Service
public class ProfessorService {

    // CRUD repository implementation
    private ProfessorRepository professorRepository;

    /**
     * Constructor.
     *
     * @param professorRepository Repository implementation to save to database.
     */
    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    /**
     * Create or Update a professor.
     *
     * @param professor Professor details to create or update.
     *
     * @return Updated professor details
     */
    public Professor createOrUpdateProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    /**
     * List all professors.
     *
     * @return list of existing professors.
     */
    public List<Professor> listProfessors() {
        return professorRepository.findAll();
    }

    /**
     * Select a particular professor by id
     *
     * @param id professor Id
     * @return professor identified by id
     */
    public Optional<Professor> selectAProfessorById(long id) {
        return professorRepository.findById(id);
    }

    public List<Professor> listAllProfessorsById(List<Long> professors) {
        return professorRepository.findAllById(professors);
    }
}
