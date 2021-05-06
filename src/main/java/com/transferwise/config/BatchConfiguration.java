package com.transferwise.config;



import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.client.RestTemplate;

import com.transferwise.model.Customer;
import com.transferwise.service.CustomerListner;



@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Value("${input}")
	Resource inputResource;

	@Value("${chunkSize}")
	int chunkSize;

	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
			ItemReader<Customer> itemReader, ItemProcessor<Customer, Customer> itemProcessor,
			ItemWriter<Customer> itemWriter) {

		Step step = stepBuilderFactory.get(inputResource + "-load").<Customer, Customer>chunk(chunkSize)
				.reader(itemReader).faultTolerant().skipPolicy(fileVerificationSkipper()).processor(itemProcessor)
				.writer(itemWriter).build();

		return jobBuilderFactory.get(" ").incrementer(new RunIdIncrementer()).listener(new CustomerListner())
				.start(step).build();
	}

	@StepScope
	@Bean
	public FlatFileItemReader<Customer> itemReader() {

		FlatFileItemReader<Customer> flatFileItemReader = new FlatFileItemReader<>();
		flatFileItemReader.setResource(inputResource);
		flatFileItemReader.setName("CSV-Reader");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapper());
		return flatFileItemReader;
	}

	@Bean
	public LineMapper<Customer> lineMapper() {

		DefaultLineMapper<Customer> defaultLineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames(new String[] { "id", "transactionId", "CustomerId", "CustomerName", "bankName",
				"transactionAmount", "merchantName", "merchantType", "transactionCountry", "transactionType" });

		BeanWrapperFieldSetMapper<Customer> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Customer.class);
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);

		return defaultLineMapper;
	}

	

	@Bean
	public SkipPolicy fileVerificationSkipper() {
		return new FileVerificationSkipper();
	}

	

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
