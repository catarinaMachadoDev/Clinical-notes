package com.catarina.clinical_notes_api.controller;


import com.catarina.clinical_notes_api.model.ClinicalNote;
import com.catarina.clinical_notes_api.service.ClinicalNoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClinicalNoteController.class)
class ClinicalNoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClinicalNoteService service;


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAll_returns200WithNotes() throws Exception {
        ClinicalNote note = ClinicalNote.builder()
                .id(1L).patientId("P001")
                .content("Fever").authorName("Catarina")
                .createdAt(LocalDateTime.now())
                .build();

        when(service.getAll()).thenReturn(List.of(note));

        mockMvc.perform(get("/api/notes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].patientId").value("P001"));
    }

    @Test
    void getById_returns200_whenExists() throws Exception {
        ClinicalNote note = ClinicalNote.builder()
                .id(1L).patientId("P001")
                .content("Fever").authorName("Catarina")
                .createdAt(LocalDateTime.now())
                .build();

        when(service.getById(1L)).thenReturn(note);

        mockMvc.perform(get("/api/notes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patientId").value("P001"));
    }

    @Test
    void getByPatient_returns200WithNotes() throws Exception {
        ClinicalNote note = ClinicalNote.builder()
                .id(1L).patientId("P001")
                .content("Fever").authorName("Catarina")
                .createdAt(LocalDateTime.now())
                .build();

        when(service.getByPatientId("P001")).thenReturn(List.of(note));

        mockMvc.perform(get("/api/notes/patient/P001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].patientId").value("P001"));
    }

    @Test
    void create_returns201() throws Exception {
        ClinicalNote note = ClinicalNote.builder()
                .id(1L).patientId("P001")
                .content("Fever").authorName("Catarina")
                .createdAt(LocalDateTime.now())
                .build();

        when(service.create(any())).thenReturn(note);

        mockMvc.perform(post("/api/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(note)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.patientId").value("P001"));
    }

    @Test
    void delete_returns204() throws Exception {
        mockMvc.perform(delete("/api/notes/1"))
                .andExpect(status().isNoContent());

        verify(service, times(1)).delete(1L);
    }
}