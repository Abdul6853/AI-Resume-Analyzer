package com.example.ResumeAnalyxer.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Analysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer analysisId;

    private Integer atsScore;

    @ElementCollection
    private List<String> matchedSkills;

   @ElementCollection
    private List<String> missingSkills;

    @Column(columnDefinition = "TEXT")
    private String strengths;

    @Column(columnDefinition = "TEXT")
    private String weaknesses;

    @Column(columnDefinition = "TEXT")
    private String suggestions;

    private LocalDateTime analyzedDate;

    @ManyToOne
    @JoinColumn(name = "resumeId")
    private Resume resume;

    @ManyToOne
    @JoinColumn(name = "jobId")
    private JobDescription jobDescription;

}
