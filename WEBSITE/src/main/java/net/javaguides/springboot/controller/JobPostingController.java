package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.JobPosting;
import net.javaguides.springboot.repository.JobPostingRepository;

@RestController
@RequestMapping("/api/job-postings")
public class JobPostingController {

    private final JobPostingRepository jobPostingRepository;

    public JobPostingController(JobPostingRepository jobPostingRepository) {
        this.jobPostingRepository = jobPostingRepository;
    }

    @GetMapping
    public List<JobPosting> getAllJobPostings() {
        return jobPostingRepository.findAll();
    }

    @PostMapping
    public JobPosting createJobPosting( @RequestBody JobPosting jobPosting) {
        return jobPostingRepository.save(jobPosting);
    }

    @GetMapping("/{id}")
    public JobPosting getJobPostingById(@PathVariable Long id) {
        try {
			return jobPostingRepository.findById(id)
			        .orElseThrow(() -> new ResourceNotFoundException("NOT_FOUND"));
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }

    @PutMapping("/{id}")
    public JobPosting updateJobPosting(@PathVariable Long id, @RequestBody JobPosting jobPostingDetails) {
        JobPosting jobPosting = null;
		try {
			jobPosting = jobPostingRepository.findById(id)
			        .orElseThrow(() -> new ResourceNotFoundException("NOT_FOUND"));
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        jobPosting.setTitle(jobPostingDetails.getTitle());
        jobPosting.setDescription(jobPostingDetails.getDescription());
        jobPosting.setLocation(jobPostingDetails.getLocation());
        jobPosting.setCompany(jobPostingDetails.getCompany());
        jobPosting.setEmploymentType(jobPostingDetails.getEmploymentType());

        JobPosting updatedJobPosting = jobPostingRepository.save(jobPosting);
        return updatedJobPosting;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJobPosting(@PathVariable Long id) {
        JobPosting jobPosting = null;
		try {
			jobPosting = jobPostingRepository.findById(id)
			        .orElseThrow(() -> new ResourceNotFoundException("NOT_FOUND"));
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        jobPostingRepository.delete(jobPosting);
        return ResponseEntity.ok().build();
    }
}

