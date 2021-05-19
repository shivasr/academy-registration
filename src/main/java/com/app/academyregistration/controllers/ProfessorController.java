package com.app.academyregistration.controllers;

import com.app.academyregistration.exceptions.ApplicationException;
import com.app.academyregistration.models.Professor;
import com.app.academyregistration.services.ProfessorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/professors")
public class ProfessorController {

    private final ProfessorService professorService;

    /**
     * Constructor
     *
     * @param professorService service implementation to mangae services.
     */
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    /**
     * List all professors.
     *
     * @return List of professors
     */
    @GetMapping
    public @ResponseBody
    ResponseEntity<List<Professor>> listProfessors() {
        List<Professor> professors = professorService.listProfessors();
        return ResponseEntity.ok(professors);
    }

    /**
     * Create professor
     *
     * @param professor Professor details to be created
     * @return response with created professor
     */
    @PostMapping
    public @ResponseBody
    ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) {
       Professor savedProfessor = professorService.createOrUpdateProfessor(professor);
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("/professors/{id}")
                .buildAndExpand(savedProfessor.getId());
        return ResponseEntity.created(uriComponents.toUri()).body(savedProfessor);
    }

    /**
     * Retrieve a professor.
     *
     * @return List of professors
     */
    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<Professor> listProfessors(@PathVariable("id") long id) {
        Professor professor = professorService.selectAProfessorById(id).orElseThrow(() ->
                new ApplicationException(String.format("Unknown professor id: %d", id)));

        return ResponseEntity.ok(professor);
    }
}
