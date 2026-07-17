package net.ent.etnc.seigneuriespring.batchs.seigneurie;

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
public class SeigneurieReaderConfig {

    @NonNull
    private final FieldSetMapperSeigneurie fieldSetMapperSeigneurie;

    @Bean
    public FlatFileItemReader<SeigneurieLineCSV> seigneurieReader() {
        return new FlatFileItemReaderBuilder<SeigneurieLineCSV>()
                .name("seigneurieReader")
                .resource(new FileSystemResource(Objects.requireNonNull(SeigneurieReaderConfig.class.getResource("/csv/seigneurie.csv")).getPath()))
                .linesToSkip(1)
                .delimited()
                .delimiter(";")
                .names("id", "date_creation", "nom", "seigneur_id")
                .fieldSetMapper(fieldSetMapperSeigneurie)
                .build();
    }
}