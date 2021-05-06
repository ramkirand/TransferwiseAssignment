package com.transferwise.config;

import java.io.FileNotFoundException;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.file.FlatFileParseException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileVerificationSkipper implements SkipPolicy {
	StringBuilder errorMessage = new StringBuilder();

	@Override
	public boolean shouldSkip(Throwable exception, int skipCount) throws SkipLimitExceededException {
		if (exception instanceof FileNotFoundException) {
			return false;
		} else if (exception instanceof FlatFileParseException && skipCount <= 1) {
			FlatFileParseException ffpe = (FlatFileParseException) exception;
			errorMessage.append("An error occured while processing line no " + ffpe.getLineNumber());
			errorMessage.append(ffpe.getInput() + "\n");
			log.error("{}", errorMessage.toString());
			return true;
		} else {
			return false;
		}
	}
}
