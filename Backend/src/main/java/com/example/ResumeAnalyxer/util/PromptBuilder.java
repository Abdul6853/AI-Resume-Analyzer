package com.example.ResumeAnalyxer.util;

public class PromptBuilder {

        public static String buildAnalysisPrompt(String resumeText, String jobDescription) {

            return """
                You are an ATS Resume Analyzer.
                
                Compare the resume with the job description.
                
                Return ONLY valid JSON.
                
                {
                    "matchedSkills": [],
                    "missingSkills": [],
                    "strengths": "",
                    "weaknesses": "",
                    "suggestions": ""
                }
                
                Rules:
                1. Do not return markdown.
                2. Do not return explanation outside JSON.
                3. matchedSkills and missingSkills must be JSON arrays.
                4. Do NOT calculate ATS Score.
                Resume:
                %s

                Job Description:
                %s
                """.formatted(resumeText, jobDescription);
        }

}
