package com.catarina.clinical_notes_api.controller;


import com.catarina.clinical_notes_api.model.ClinicalNote;
import com.catarina.clinical_notes_api.service.ClinicalNoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class ClinicalNoteController {

    private final ClinicalNoteService service;

    @GetMapping
    public List<ClinicalNote> getAll() {
        return service.getAll();
    }

    @GetMapping("/patient/{patientId}")
    public List<ClinicalNote> getByPatient(@PathVariable String patientId) {
        return service.getByPatientId(patientId);
    }

    @GetMapping("/{id}")
    public ClinicalNote getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClinicalNote create(@RequestBody ClinicalNote note) {
        return service.create(note);
    }

    @PutMapping("/{id}")
    public ClinicalNote update(@PathVariable Long id, @RequestBody ClinicalNote note) {
        return service.update(id, note);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}