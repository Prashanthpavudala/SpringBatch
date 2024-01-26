package com.learning.springbatchprocess.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JobMonitor implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Inside JobMonitor beforeJob method with jobInstance {}", jobExecution.getJobInstance());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("Inside JobMonitor afterJob method with jobStatus {}", jobExecution.getStatus());
    }
}
