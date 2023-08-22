package com.codespark.springbootfilebatch.batchprocessing;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.codespark.springbootfilebatch.batchprocessing.job.EmployeeJobListener;

@Configuration
public class EmployeeJobConfig {

        @Bean
        public Step batchJobStep1(JobRepository jobRepository,
                        PlatformTransactionManager transactionManager, EmployeeJobListener employeeJobListener,
                        ItemReader<Employee> employeeFileItemReader,
                        ItemProcessor<Employee, Employee> employeeItemProcessor,
                        ItemWriter<Employee> employeeItemWriter) {
                return new StepBuilder("BATCH_STEP_1", jobRepository)
                                .<Employee, Employee>chunk(2, transactionManager)
                                .reader(employeeFileItemReader)
                                .processor(employeeItemProcessor)
                                .writer(employeeItemWriter)
                                .build();
        }

        @Bean
        public Job employeeBatchJob(JobRepository jobRepository, EmployeeJobListener employeeJobListener,
                        Step batchJobStep1) {
                return new JobBuilder("BATCH_JOB_1", jobRepository)
                                .incrementer(new RunIdIncrementer())
                                .listener(employeeJobListener)
                                .flow(batchJobStep1)
                                .end()
                                .build();
        }

        @Bean("employeeFileItemReader")
        public FlatFileItemReader<Employee> fileReader() {
                BeanWrapperFieldSetMapper<Employee> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
                beanWrapperFieldSetMapper.setTargetType(Employee.class);

                // Setup the data CSV file path and field names
                return new FlatFileItemReaderBuilder<Employee>().name("EMPLOYEE_FILE_READER")
                                .resource(new ClassPathResource("/static/users-data.csv")).delimited()
                                .names(new String[] { "name", "department", "salary" })
                                .fieldSetMapper(beanWrapperFieldSetMapper)
                                .build();
        }

}
