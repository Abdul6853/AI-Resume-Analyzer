package com.example.ResumeAnalyxer.Service;

import com.example.ResumeAnalyxer.Model.Resume;
import com.example.ResumeAnalyxer.Model.User;
import com.example.ResumeAnalyxer.Repo.ResumeRepo;
import com.example.ResumeAnalyxer.Repo.UserRepo;
import com.example.ResumeAnalyxer.util.pdfExtract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResumeService {
    @Autowired
    ResumeRepo resumeRepo;
    @Autowired
    UserRepo userRepo;
    public Resume addResume(int userId,Resume resume) {
         User user=userRepo.findById(userId).orElse(null);
        resume.setUser(user);
         resume.setUploadDate(LocalDateTime.now());

         return resumeRepo.save(resume);
    }

    public List<Resume> getAllResume() {
        return resumeRepo.findAll();
    }

    public Resume getById(int resumeId) {
        return resumeRepo.findById(resumeId).orElse(null);
    }

    public String updateResume(int resumeId,Resume updatedResume) {
        Resume existingResume=resumeRepo.findById(resumeId).get();
        existingResume.setResumeText(updatedResume.getResumeText());
        existingResume.setFileName(updatedResume.getFileName());
        existingResume.setFileType(updatedResume.getFileType());
        resumeRepo.save(existingResume);
        return existingResume.getResumeId()+" has been updated sucessfully";

    }

    public String deleteResumebyId(int resumeId) {
        resumeRepo.deleteById(resumeId);
        return resumeId+" has been deleted ";
    }

    public Resume uploadResume(int userId, MultipartFile file) throws IOException {
        User user= userRepo.findById(userId).orElse(null);
        if (user==null){ throw new RuntimeException("User with ID " + userId + " not found");}
        if(file.isEmpty()){ throw new RuntimeException("Please Upload a file"); }
        Resume resume= new Resume();
        resume.setFileName(file.getOriginalFilename());
        resume.setFileType(file.getContentType());
        resume.setFileSize(file.getSize());
        resume.setUploadDate(LocalDateTime.now());

        String text = pdfExtract.extractText(file);
        resume.setResumeText(text);
        resume.setUser(user);
        return resumeRepo.save(resume);



    }
}
