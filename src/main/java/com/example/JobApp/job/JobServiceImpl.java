package com.example.JobApp.job;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService{

    private List<Job> jobs = new ArrayList<Job>();
    Long nextId = 1L;


    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobs.add(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobs.stream().filter(job -> job.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public boolean deleteJob(Long id) {
        Job job = getJobById(id);
        return jobs.remove(job);
    }

    @Override
    public boolean updateJob(Long id, Job job) {
        Job oldJob = getJobById(id);
        if(oldJob == null) {
            return false;
        }
        oldJob.setTitle(job.getTitle());
        oldJob.setDescription(job.getDescription());
        oldJob.setLocation(job.getLocation());
        oldJob.setMinSalary(job.getMinSalary());
        oldJob.setMaxSalary(job.getMaxSalary());
        return true;
    }
}
