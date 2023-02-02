package net.javaguides.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.model.JobPosting;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
}

