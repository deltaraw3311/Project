package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.JobPosting;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
}
