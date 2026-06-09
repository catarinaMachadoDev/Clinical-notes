package com.catarina.clinical_notes_api.repository;

import com.catarina.clinical_notes_api.model.ClinicalNote;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClinicalNoteRepository extends JpaRepository<ClinicalNote, Long> {
    List<ClinicalNote> findByPatientId(String patientId);
}