package com.example.ResumeAnalyxer.Service;

import com.example.ResumeAnalyxer.Model.Analysis;
import com.example.ResumeAnalyxer.Model.JobDescription;
import com.example.ResumeAnalyxer.Model.Resume;
import com.example.ResumeAnalyxer.Repo.AnalysisRepo;
import com.example.ResumeAnalyxer.Repo.JobDescriptionRepo;
import com.example.ResumeAnalyxer.Repo.ResumeRepo;
import com.example.ResumeAnalyxer.util.PromptBuilder;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

@Service
public class AnalysisService {
    private final ResumeRepo resumeRepo;
    private final JobDescriptionRepo jobDescriptionRepo;
    private final AnalysisRepo analysisRepo;
    private final GeminiService geminiService;

    public AnalysisService(ResumeRepo resumeRepo,
                           JobDescriptionRepo jobDescriptionRepo,
                           AnalysisRepo analysisRepo,
                           GeminiService geminiService) {

        this.resumeRepo = resumeRepo;
        this.jobDescriptionRepo = jobDescriptionRepo;
        this.analysisRepo = analysisRepo;
        this.geminiService = geminiService;
    }

    public Analysis analyzeResume(int resumeId,int jdId ) throws Exception{
        Resume resume = resumeRepo.findById(resumeId)
                .orElseThrow(() -> new RuntimeException("Resume not found"));
        JobDescription jobDescription = jobDescriptionRepo.findById(jdId)
                .orElseThrow(() -> new RuntimeException("Job Description not found"));
        String prompt = PromptBuilder.buildAnalysisPrompt(
                resume.getResumeText(),
                jobDescription.getJobDescription()
        );
        String aiResponse=geminiService.chat(prompt);
        System.out.println(aiResponse);
        //ObjectMapper helps to convert String to an object
        ObjectMapper objectMapper=new ObjectMapper();
        Analysis analysis= objectMapper.readValue(aiResponse,Analysis.class);
        //calculating ATS score manually
        int matched = analysis.getMatchedSkills().size();
        int missing = analysis.getMissingSkills().size();
        int total = matched+missing;
        int score=(matched * 100)/total;
        analysis.setAtsScore(score);
        System.out.println(analysis.getAtsScore());
        analysis.setResume(resume);
        analysis.setAnalyzedDate(LocalDateTime.now());

        return analysisRepo.save(analysis);
    }


}
