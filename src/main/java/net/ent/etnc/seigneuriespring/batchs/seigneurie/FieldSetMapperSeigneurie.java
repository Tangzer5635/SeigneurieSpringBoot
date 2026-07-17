package net.ent.etnc.seigneuriespring.batchs.seigneurie;

import org.springframework.batch.infrastructure.item.file.mapping.FieldSetMapper;
import org.springframework.batch.infrastructure.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Component
public class FieldSetMapperSeigneurie implements FieldSetMapper<SeigneurieLineCSV> {
    @Override
    public SeigneurieLineCSV mapFieldSet(FieldSet fieldSet) throws BindException {
        return new SeigneurieLineCSV(
                fieldSet.readRawString(2), //NOM
                fieldSet.readLong(3) //SEIGNEUR_ID
        );
    }
}