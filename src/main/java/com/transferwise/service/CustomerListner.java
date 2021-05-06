package com.transferwise.service;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import com.transferwise.batch.DBWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomerListner implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {

		log.info("..........  Job created time................... " + jobExecution.getCreateTime());
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			DBWriter.footerNote();
			
		}

	}

}
