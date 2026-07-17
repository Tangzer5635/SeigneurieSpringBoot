package net.ent.etnc.seigneuriespring.batchs.ressource;

import jakarta.persistence.EntityManagerFactory;
import net.ent.etnc.seigneuriespring.models.entity.Ressource;
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
public class RessourceBatch {

    @Bean
    public Step importRessourceStep(
            FlatFileItemReader<RessourceLineCSV> ressourceReader,
            RessourceProcessor ressourceProcessor,
            JpaItemWriter<Ressource> ressourceWriter,
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager) {
        return new StepBuilder("importRessourceStep", jobRepository)
                .<RessourceLineCSV, Ressource>chunk(10)
                .transactionManager(transactionManager)
                .reader(ressourceReader)
                .processor(ressourceProcessor)
                .writer(ressourceWriter)
                .build();
    }

    @Bean
    public JpaItemWriter<Ressource> ressourceWriter(EntityManagerFactory entityManager) {
        return new JpaItemWriter<>(entityManager);
    }

    @Bean
    public Job importRessourceJob(
            Step importRessourceStep,
            JobRepository jobRepository) {
        return new JobBuilder("importRessourceJob", jobRepository)
                .start(importRessourceStep)
                .build();
    }
}