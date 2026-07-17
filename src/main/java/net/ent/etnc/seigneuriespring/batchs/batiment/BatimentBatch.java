package net.ent.etnc.seigneuriespring.batchs.batiment;

import jakarta.persistence.EntityManagerFactory;
import net.ent.etnc.seigneuriespring.models.entity.Batiment;
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
public class BatimentBatch {

    @Bean
    public Step importBatimentStep(
            FlatFileItemReader<BatimentLineCSV> batimentReader,
            BatimentProcessor batimentProcessor,
            JpaItemWriter<Batiment> batimentWriter,
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager) {
        return new StepBuilder("importBatimentStep", jobRepository)
                .<BatimentLineCSV, Batiment>chunk(10)
                .transactionManager(transactionManager)
                .reader(batimentReader)
                .processor(batimentProcessor)
                .writer(batimentWriter)
                .build();
    }

    @Bean
    public JpaItemWriter<Batiment> batimentWriter(EntityManagerFactory entityManager) {
        return new JpaItemWriter<>(entityManager);
    }

    @Bean
    public Job importBatimentJob(
            Step importBatimentStep,
            JobRepository jobRepository) {
        return new JobBuilder("importBatimentJob", jobRepository)
                .start(importBatimentStep)
                .build();
    }
}
