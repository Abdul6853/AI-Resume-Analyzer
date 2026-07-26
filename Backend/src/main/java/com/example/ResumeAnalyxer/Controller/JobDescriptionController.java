package com.example.ResumeAnalyxer.Controller;

import com.example.ResumeAnalyxer.Model.JobDescription;
import com.example.ResumeAnalyxer.Service.JobDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jd")
public class JobDescriptionController {
    @Autowired
    private JobDescriptionService jobDescriptionService;

    @PostMapping("/add")
    public JobDescription addJob(@RequestBody JobDescription job) {

        return jobDescriptionService.addJob(job);
    }

    @GetMapping("/all")
    public List<JobDescription> getAllJobs() {

        return jobDescriptionService.getAllJobs();
    }

    @GetMapping("/{id}")
    public JobDescription getJob(@PathVariable int id) {

        return jobDescriptionService.getJobById(id);
    }

    @PutMapping("/update/{id}")
    public String updateJob(@PathVariable int id,
                            @RequestBody JobDescription job) {

        return jobDescriptionService.updateJob(id, job);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteJob(@PathVariable int id) {

        return jobDescriptionService.deleteJob(id);
    }

}
