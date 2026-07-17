package net.ent.etnc.seigneuriespring.batchs.habitant;

import jakarta.persistence.EntityManagerFactory;
import net.ent.etnc.seigneuriespring.models.entity.Habitant;
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
public class HabitantBatch {

    @Bean
    public Step importHabitantStep(
            FlatFileItemReader<HabitantLineCSV> habitantReader,
            HabitantProcessor habitantProcessor,
            JpaItemWriter<Habitant> habitantWriter,
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager) {
        return new StepBuilder("importHabitantStep", jobRepository)
                .<HabitantLineCSV, Habitant>chunk(10)
                .transactionManager(transactionManager)
                .reader(habitantReader)
                .processor(habitantProcessor)
                .writer(habitantWriter)
                .build();
    }

    @Bean
    public JpaItemWriter<Habitant> habitantWriter(EntityManagerFactory entityManager) {
        return new JpaItemWriter<>(entityManager);
    }

    @Bean
    public Job importHabitantJob(
            Step importHabitantStep,
            JobRepository jobRepository) {
        return new JobBuilder("importHabitantJob", jobRepository)
                .start(importHabitantStep)
                .build();
    }
}
