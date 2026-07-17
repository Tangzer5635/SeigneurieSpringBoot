package net.ent.etnc.seigneuriespring.batchs.ressource;

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
public class RessourceReaderConfig {

    @NonNull
    private final FieldSetMapperRessource fieldSetMapperRessource;

    @Bean
    public FlatFileItemReader<RessourceLineCSV> ressourceReader() {
        return new FlatFileItemReaderBuilder<RessourceLineCSV>()
                .name("ressourceReader")
                .resource(new FileSystemResource(Objects.requireNonNull(RessourceReaderConfig.class.getResource("/csv/ressource.csv")).getPath()))
                .linesToSkip(1)
                .delimited()
                .delimiter(";")
                .names("id", "date_creation", "nom", "type_ressource")
                .fieldSetMapper(fieldSetMapperRessource)
                .build();
    }
}