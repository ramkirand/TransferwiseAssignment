package com.transferwise.controller;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.transferwise.service.DataLoadService;

//import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/load")
public class LoadDataController {

	@Autowired
	DataLoadService dataLoadService;

	// @ApiOperation(value = "Import excel data resource", produces = "Status of the
	// batch Job")
	@GetMapping(value = "/transaction")
	public BatchStatus load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
			JobRestartException, JobInstanceAlreadyCompleteException {
		return dataLoadService.loadData();

	}
}
