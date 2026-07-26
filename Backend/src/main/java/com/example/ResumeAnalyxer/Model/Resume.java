package com.example.ResumeAnalyxer.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int resumeId;
    private String fileName;
    private String fileType;
    private Long fileSize;
    private LocalDateTime uploadDate;
    @Column(columnDefinition = "Text")
    private String resumeText;
    @ManyToOne
            @JoinColumn(name = "userId")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "resume")
    @JsonIgnore
    private List<Analysis> analyses;

}
