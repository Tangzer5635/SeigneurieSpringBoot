package net.ent.etnc.seigneuriespring.batchs.batiment;

import net.ent.etnc.seigneuriespring.models.referencies.TypeBat;
import org.springframework.batch.infrastructure.item.file.mapping.FieldSetMapper;
import org.springframework.batch.infrastructure.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Component
public class FieldSetMapperBatiment implements FieldSetMapper<BatimentLineCSV> {
    @Override
    public BatimentLineCSV mapFieldSet(FieldSet fieldSet) throws BindException {
        return new BatimentLineCSV(
                fieldSet.readRawString(2),
                fieldSet.readBoolean(3),
                TypeBat.valueOf(fieldSet.readRawString(4))
        );
    }
}
