package com.transferwise.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.transferwise.service.DataLoadService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DataLoadServiceImpl implements DataLoadService{

	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	private Job job;

	@Override
	public BatchStatus loadData() {
		Map<String, JobParameter> maps = new HashMap<>();
		maps.put("time", new JobParameter(new Date(System.currentTimeMillis())));

		JobParameters parameters = new JobParameters(maps);
		JobExecution jobExecution = new JobExecution(System.currentTimeMillis());
		try {
			jobExecution = jobLauncher.run(job, parameters);
			log.info("JobExecution: " + jobExecution.getStatus());
			log.info("....................Batch is Running...............................");
			while (jobExecution.isRunning()) {
				log.info("...");

			}
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			log.debug(e.getMessage());
		}

		return jobExecution.getStatus();
	}

	

}
