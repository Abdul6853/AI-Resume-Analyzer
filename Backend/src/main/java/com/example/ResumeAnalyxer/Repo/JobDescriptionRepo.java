package com.example.ResumeAnalyxer.Repo;

import com.example.ResumeAnalyxer.Model.JobDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobDescriptionRepo extends JpaRepository<JobDescription,Integer> {
}
