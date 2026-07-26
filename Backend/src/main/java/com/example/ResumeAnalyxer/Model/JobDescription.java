package com.example.ResumeAnalyxer.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class JobDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jdId;
    private String companyName;
    private String jobTitle;

    @Column(columnDefinition = "TEXT")
    private String jobDescription;
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "jobDescription")
    private List<Analysis> analyses;
}
