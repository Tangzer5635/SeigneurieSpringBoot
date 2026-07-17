package net.ent.etnc.seigneuriespring.batchs.evenement;

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
public class EvenementReaderConfig {

    @NonNull
    private final FieldSetMapperEvenement fieldSetMapperEvenement;

    @Bean
    public FlatFileItemReader<EvenementLineCSV> evenementReader() {
        return new FlatFileItemReaderBuilder<EvenementLineCSV>()
                .name("evenementReader")
                .resource(new FileSystemResource(Objects.requireNonNull(EvenementReaderConfig.class.getResource("/csv/evenement.csv")).getPath()))
                .linesToSkip(1)
                .delimited()
                .delimiter(";")
                .names("id", "date_creation", "nom", "description", "date_debut", "date_fin", "type_batiment")
                .fieldSetMapper(fieldSetMapperEvenement)
                .build();
    }
}