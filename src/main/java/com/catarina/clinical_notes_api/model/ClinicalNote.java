package com.catarina.clinical_notes_api.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "clinical_notes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ClinicalNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String patientId;

    @Column(nullable = false, length = 2000)
    private String content;

    @Column(nullable = false)
    private String authorName;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}