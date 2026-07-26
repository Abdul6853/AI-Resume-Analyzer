package com.example.ResumeAnalyxer.Repo;

import com.example.ResumeAnalyxer.Model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepo extends JpaRepository<Resume,Integer> {
}
