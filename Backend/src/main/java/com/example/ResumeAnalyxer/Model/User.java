package com.example.ResumeAnalyxer.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class User {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
    private String name;
    private String email;
    private LocalDateTime createdDate;
    private String password;
    @OneToMany(mappedBy ="user" )
    @JsonManagedReference
    private List<Resume > resumes;
}
