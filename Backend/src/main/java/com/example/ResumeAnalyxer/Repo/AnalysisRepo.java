package com.example.ResumeAnalyxer.Repo;

import com.example.ResumeAnalyxer.Model.Analysis;
import com.example.ResumeAnalyxer.Model.JobDescription;
import com.example.ResumeAnalyxer.Model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnalysisRepo extends JpaRepository<Analysis,Integer> {
   Optional<Analysis>  findByResumeAndJobDescription(Resume resume, JobDescription jobDescription);
}
