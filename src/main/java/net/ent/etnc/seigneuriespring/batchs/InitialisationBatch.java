package net.ent.etnc.seigneuriespring.batchs;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitialisationBatch {

    @Bean
    public Job initialisationJob(
            JobRepository jobRepository,
            Step importHabitantStep,
            Step importSeigneurieStep,
            Step importBatimentStep,
            Step importEvenementStep,
            Step importRessourceStep) {
        return new JobBuilder("initialisationJob", jobRepository)
                .start(importHabitantStep)
                .next(importSeigneurieStep)
                .next(importBatimentStep)
                .next(importEvenementStep)
                .next(importRessourceStep)
                .build();
    }
}