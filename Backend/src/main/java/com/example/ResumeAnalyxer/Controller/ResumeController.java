package com.example.ResumeAnalyxer.Controller;

import com.example.ResumeAnalyxer.Model.Resume;
import com.example.ResumeAnalyxer.Service.ResumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@Tag(name = "Resume Management", description = "APIs for uploading and managing resumes")
public class ResumeController {
    @Autowired
    ResumeService resumeService;

    //Upload resume
    @Operation(
            summary = "Upload Resume",
            description = "Uploads a PDF resume for a specific user."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Resume uploaded successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PostMapping("user/{userId}/resume")
    public Resume addResume(@RequestBody Resume resume,
                            @PathVariable int userId){
        return resumeService.addResume(userId,resume);

    }
    //GEt all resume
    @GetMapping("resume/all/resume")
    public List<Resume> getAllResume(){
        return resumeService.getAllResume();
    }
    //get resume by ID
    @GetMapping("resume/by/id/{resumeId}")
    public Resume getById(@PathVariable int resumeId){
        return resumeService.getById(resumeId);
    }
    // Update resume by Id
    @PutMapping("resume/update/{resumeId}")
    public String updateResume(@PathVariable int resumeId,
                               @RequestBody Resume updatedResume){
        return resumeService.updateResume(resumeId,updatedResume);
    }

    //DeleteResumeById
    @DeleteMapping("resume/delete/{resumeId}")
    public String deleteResumeById(@PathVariable int resumeId){
        return resumeService.deleteResumebyId(resumeId);
    }
    //Upload Resume
    @PostMapping("resume/user/{userId}/upload")
    public Resume uploadResume(@PathVariable int userId,
                               @RequestParam("file") MultipartFile file) throws IOException {
        return resumeService.uploadResume(userId,file);
    }
}
