package net.ent.etnc.seigneuriespring.batchs.habitant;

import net.ent.etnc.seigneuriespring.models.referencies.StatutHabitant;
import org.springframework.batch.infrastructure.item.file.mapping.FieldSetMapper;
import org.springframework.batch.infrastructure.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import java.time.LocalDate;
import java.util.Objects;

@Component
public class FieldSetMapperHabitant implements FieldSetMapper<HabitantLineCSV> {
    @Override
    public HabitantLineCSV mapFieldSet(FieldSet fieldSet) throws BindException {

        return new HabitantLineCSV(
                fieldSet.readRawString(2), //NOM
                fieldSet.readRawString(3), //PRENOM
                LocalDate.parse(Objects.requireNonNull(fieldSet.readRawString(4))), //DATE_NAISSANCE
                StatutHabitant.valueOf(fieldSet.readRawString(5)) //STATUE
        );
    }
}
