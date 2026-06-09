package com.catarina.clinical_notes_api.service;


import com.catarina.clinical_notes_api.model.ClinicalNote;
import com.catarina.clinical_notes_api.repository.ClinicalNoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClinicalNoteService {

    private final ClinicalNoteRepository repository;

    public List<ClinicalNote> getAll() {
        return repository.findAll();
    }

    public List<ClinicalNote> getByPatientId(String patientId) {
        return repository.findByPatientId(patientId);
    }

    public ClinicalNote getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found with id: " + id));
    }

    public ClinicalNote create(ClinicalNote note) {
        return repository.save(note);
    }

    public ClinicalNote update(Long id, ClinicalNote updated) {
        ClinicalNote existing = getById(id);
        existing.setPatientId(updated.getPatientId());
        existing.setContent(updated.getContent());
        existing.setAuthorName(updated.getAuthorName());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}