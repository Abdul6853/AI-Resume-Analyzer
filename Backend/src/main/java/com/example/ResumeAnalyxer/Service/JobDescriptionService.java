package com.example.ResumeAnalyxer.Service;

import com.example.ResumeAnalyxer.Model.JobDescription;
import com.example.ResumeAnalyxer.Repo.JobDescriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobDescriptionService {
    @Autowired
    private JobDescriptionRepo jobDescriptionRepo;

    public JobDescription addJob(JobDescription job) {

        job.setCreatedDate(LocalDateTime.now());

        return jobDescriptionRepo.save(job);

    }

    public List<JobDescription> getAllJobs() {

        return jobDescriptionRepo.findAll();
    }

    public JobDescription getJobById(int id) {

        return jobDescriptionRepo.findById(id).orElse(null);
    }
    public String updateJob(int id, JobDescription updatedJob) {

        JobDescription existingJob = jobDescriptionRepo.findById(id).orElse(null);

        if (existingJob == null) {
            return "Job Not Found";
        }

        existingJob.setJobTitle(updatedJob.getJobTitle());
        existingJob.setCompanyName(updatedJob.getCompanyName());
        existingJob.setJobDescription(updatedJob.getJobDescription());

        jobDescriptionRepo.save(existingJob);

        return "Job Updated Successfully";
    }
    public String deleteJob(int id) {

        if (!jobDescriptionRepo.existsById(id)) {
            return "Job Not Found";
        }

        jobDescriptionRepo.deleteById(id);

        return "Job Deleted Successfully";
    }

}
