package com.example.ResumeAnalyxer.Controller;

import com.example.ResumeAnalyxer.Model.Analysis;
import com.example.ResumeAnalyxer.Service.AnalysisService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnalysisController {
    private AnalysisService analysisService;
    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }


    @PostMapping("analysis/{resumeId}/{jdId}")
    public Analysis analyzeResume(@PathVariable int resumeId,
                                  @PathVariable int jdId) throws Exception {

        return analysisService.analyzeResume(resumeId, jdId);
    }
}
