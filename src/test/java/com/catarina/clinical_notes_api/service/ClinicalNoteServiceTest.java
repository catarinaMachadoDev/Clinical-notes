package com.catarina.clinical_notes_api.service;


import com.catarina.clinical_notes_api.model.ClinicalNote;
import com.catarina.clinical_notes_api.repository.ClinicalNoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClinicalNoteServiceTest {

    @Mock
    private ClinicalNoteRepository repository;

    @InjectMocks
    private ClinicalNoteService service;

    @Test
    void getAll_returnsAllNotes() {
        ClinicalNote note = ClinicalNote.builder()
                .id(1L).patientId("P001")
                .content("Fever").authorName("Catarina")
                .build();

        when(repository.findAll()).thenReturn(List.of(note));

        List<ClinicalNote> result = service.getAll();

        assertEquals(1, result.size());
        assertEquals("P001", result.get(0).getPatientId());
        verify(repository, times(1)).findAll();
    }

    @Test
    void getById_returnsNote_whenExists() {
        ClinicalNote note = ClinicalNote.builder()
                .id(1L).patientId("P001")
                .content("Fever").authorName("Catarina")
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(note));

        ClinicalNote result = service.getById(1L);

        assertEquals(1L, result.getId());
        assertEquals("P001", result.getPatientId());
    }

    @Test
    void getById_throwsException_whenNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.getById(99L));

        assertEquals("Note not found with id: 99", ex.getMessage());
    }

    @Test
    void create_savesAndReturnsNote() {
        ClinicalNote note = ClinicalNote.builder()
                .patientId("P001").content("Fever").authorName("Catarina")
                .build();

        when(repository.save(note)).thenReturn(note);

        ClinicalNote result = service.create(note);

        assertEquals("P001", result.getPatientId());
        verify(repository, times(1)).save(note);
    }

    @Test
    void delete_callsRepository() {
        service.delete(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}