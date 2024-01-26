package com.learning.springbatchprocess.config;

import com.learning.springbatchprocess.component.processor.UserDataProcessor;
import com.learning.springbatchprocess.component.reader.UserDataReader;
import com.learning.springbatchprocess.component.writer.UserDataWriter;
import com.learning.springbatchprocess.domain.UserInfo;
import com.learning.springbatchprocess.dto.UserDTO;
import com.learning.springbatchprocess.utils.Constants;
import com.learning.springbatchprocess.utils.JobMonitor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@Slf4j
public class BatchJobConfig {

    @Autowired
    private JobMonitor jobMonitor;

    @Autowired
    private UserDataReader userDataReader;

    @Autowired
    private UserDataProcessor userDataProcessor;

    @Autowired
    private UserDataWriter userDataWriter;

    @Value("${spring.batch.job.name}")
    private String jobName;

    @Bean
    public Job job(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        log.info("Initializing the job...");
        return new JobBuilder(jobName, jobRepository)
                .preventRestart()
                .incrementer(new RunIdIncrementer())
                .start(exportUserDataStep(jobRepository, transactionManager))
                .listener(jobMonitor)
                .build();
    }

    @Bean
    protected Step exportUserDataStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        log.info("Inside exportUserDataStep");
        return new StepBuilder(Constants.EXPORT_USER_DATA_STEP, jobRepository)
                .<UserDTO, UserInfo>chunk(Constants.CHUNK_SIZE, transactionManager)
                .faultTolerant()
                .reader(userDataReader)
                .processor(userDataProcessor)
                .writer(userDataWriter)
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    protected ThreadPoolTaskExecutor taskExecutor() {
        int maxQueueCapacity = 1 + Constants.THREAD_SIZE;
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setQueueCapacity(maxQueueCapacity);
        executor.setThreadNamePrefix("WORKER-");
        executor.afterPropertiesSet();
        executor.setDaemon(true);
        return executor;
    }

}
