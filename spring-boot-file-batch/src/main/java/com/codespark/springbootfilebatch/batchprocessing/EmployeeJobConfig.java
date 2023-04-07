package com.codespark.springbootfilebatch.batchprocessing;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.codespark.springbootfilebatch.batchprocessing.job.EmployeeJobListener;

@Configuration
@EnableBatchProcessing
public class EmployeeJobConfig {

        @Autowired
        public JobBuilderFactory jobBuilderFactory;

        @Autowired
        public StepBuilderFactory stepBuilderFactory;

        @Bean
        public Job employeeBatchJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                        EmployeeJobListener employeeJobListener, ItemReader<Employee> employeeFileItemReader,
                        ItemProcessor<Employee, Employee> employeeItemProcessor,
                        ItemWriter<Employee> employeeItemWriter) {
                // Step: Reader -> Processor -> Writer
                Step step = stepBuilderFactory.get("BATCH_STEP_1").<Employee, Employee>chunk(2)
                                .reader(employeeFileItemReader).processor(employeeItemProcessor)
                                .writer(employeeItemWriter)
                                .build();

                // Job: Step1 -> Step2 -> End
                Job job = jobBuilderFactory.get("BATCH_JOB_1").incrementer(new RunIdIncrementer())
                                .listener(employeeJobListener).flow(step)
                                .end().build();

                return job;
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
