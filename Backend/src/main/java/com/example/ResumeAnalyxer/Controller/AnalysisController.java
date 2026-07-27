package com.example.ResumeAnalyxer.Controller;

import com.example.ResumeAnalyxer.Model.Analysis;
import com.example.ResumeAnalyxer.Service.AnalysisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Resume Analysis", description = "AI powered resume analysis APIs")
public class AnalysisController {
    private AnalysisService analysisService;
    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @Operation(
            summary = "Analyze Resume",
            description = "Analyzes the uploaded resume against a job description using Gemini AI."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Analysis completed"),
            @ApiResponse(responseCode = "404", description = "Resume or Job Description not found")
    })
    @PostMapping("analysis/{resumeId}/{jdId}")
    public Analysis analyzeResume(@PathVariable int resumeId,
                                  @PathVariable int jdId) throws Exception {

        return analysisService.analyzeResume(resumeId, jdId);
    }
}
