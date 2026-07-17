package net.ent.etnc.seigneuriespring.batchs.evenement;

import jakarta.persistence.EntityManagerFactory;
import net.ent.etnc.seigneuriespring.models.entity.Evenement;
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
public class EvenementBatch {

    @Bean
    public Step importEvenementStep(
            FlatFileItemReader<EvenementLineCSV> evenementReader,
            EvenementProcessor evenementProcessor,
            JpaItemWriter<Evenement> evenementWriter,
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager) {
        return new StepBuilder("importEvenementStep", jobRepository)
                .<EvenementLineCSV, Evenement>chunk(10)
                .transactionManager(transactionManager)
                .reader(evenementReader)
                .processor(evenementProcessor)
                .writer(evenementWriter)
                .build();
    }

    @Bean
    public JpaItemWriter<Evenement> evenementWriter(EntityManagerFactory entityManager) {
        return new JpaItemWriter<>(entityManager);
    }

    @Bean
    public Job importEvenementJob(
            Step importEvenementStep,
            JobRepository jobRepository) {
        return new JobBuilder("importEvenementJob", jobRepository)
                .start(importEvenementStep)
                .build();
    }
}