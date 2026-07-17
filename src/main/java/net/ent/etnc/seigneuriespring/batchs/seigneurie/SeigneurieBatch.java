package net.ent.etnc.seigneuriespring.batchs.seigneurie;

import jakarta.persistence.EntityManagerFactory;
import net.ent.etnc.seigneuriespring.models.entity.Seigneurie;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.database.JpaItemWriter;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class SeigneurieBatch {

    @Bean
    public Step importSeigneurieStep(
            FlatFileItemReader<SeigneurieLineCSV> seigneurieReader,
            SeigneurieProcessor seigneurieProcessor,
            JpaItemWriter<Seigneurie> seigneurieWriter,
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager) {
        return new StepBuilder("importSeigneurieStep", jobRepository)
                .<SeigneurieLineCSV, Seigneurie>chunk(10)
                .transactionManager(transactionManager)
                .reader(seigneurieReader)
                .processor(seigneurieProcessor)
                .writer(seigneurieWriter)
                .build();
    }

    @Bean
    public JpaItemWriter<Seigneurie> seigneurieWriter(EntityManagerFactory entityManager) {
        return new JpaItemWriter<>(entityManager);
    }

    @Bean
    public Job importSeigneurieJob(
            Step importSeigneurieStep,
            JobRepository jobRepository) {
        return new JobBuilder("importSeigneurieJob", jobRepository)
                .start(importSeigneurieStep)
                .build();
    }
}