package net.ent.etnc.seigneuriespring.batchs.habitant;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class HabitantReaderConfig {

    @NonNull
    private final FieldSetMapperHabitant fieldSetMapperHabitant;

    @Bean
    public FlatFileItemReader<HabitantLineCSV> habitantReader() {
        return new FlatFileItemReaderBuilder<HabitantLineCSV>()
                .name("habitantReader")
                .resource(new FileSystemResource(Objects.requireNonNull(HabitantReaderConfig.class.getResource("/csv/habitant.csv")).getPath()))
                .linesToSkip(1)
                .delimited()
                .delimiter(";")
                .names("id", "date_creation", "nom", "prenom", "date_naissance", "statue", "seigneurie_id")
                .fieldSetMapper(fieldSetMapperHabitant)
//                .targetType(HabitantLineCSV.class)
                .build();

    }
}
