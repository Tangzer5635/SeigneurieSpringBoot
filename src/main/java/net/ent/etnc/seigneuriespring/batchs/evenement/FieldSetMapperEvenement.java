package net.ent.etnc.seigneuriespring.batchs.evenement;

import net.ent.etnc.seigneuriespring.models.referencies.TypeEvenement;
import org.springframework.batch.infrastructure.item.file.mapping.FieldSetMapper;
import org.springframework.batch.infrastructure.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class FieldSetMapperEvenement implements FieldSetMapper<EvenementLineCSV> {
    @Override
    public EvenementLineCSV mapFieldSet(FieldSet fieldSet) throws BindException {
        return new EvenementLineCSV(
                fieldSet.readRawString(2), //NOM
                fieldSet.readRawString(3), //DESCRIPTION
                LocalDateTime.parse(Objects.requireNonNull(fieldSet.readRawString(4))), //DATE_DEBUT
                LocalDateTime.parse(Objects.requireNonNull(fieldSet.readRawString(5))), //DATE_FIN
                TypeEvenement.valueOf(fieldSet.readRawString(6)) //TYPE
        );
    }
}