package net.ent.etnc.seigneuriespring.batchs.batiment;

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
public class BatimentReaderConfig {

    @NonNull
    private final FieldSetMapperBatiment fieldSetMapperBatiment;

    @Bean
    public FlatFileItemReader<BatimentLineCSV> batimentReader() {
        return new FlatFileItemReaderBuilder<BatimentLineCSV>()
                .name("batimentReader")
                .resource(new FileSystemResource(Objects.requireNonNull(BatimentReaderConfig.class.getResource("/csv/batiment.csv")).getPath()))
                .linesToSkip(1)
                .delimited()
                .delimiter(";")
                .names("id", "date_creation", "nom", "est_actif", "type", "seigneurie_id")
                .fieldSetMapper(fieldSetMapperBatiment)
                .build();
    }
}
