package net.ent.etnc.seigneuriespring.batchs.ressource;

import net.ent.etnc.seigneuriespring.models.referencies.TypeRessource;
import org.springframework.batch.infrastructure.item.file.mapping.FieldSetMapper;
import org.springframework.batch.infrastructure.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Component
public class FieldSetMapperRessource implements FieldSetMapper<RessourceLineCSV> {
    @Override
    public RessourceLineCSV mapFieldSet(FieldSet fieldSet) throws BindException {
        return new RessourceLineCSV(
                fieldSet.readRawString(2), //NOM
                TypeRessource.valueOf(fieldSet.readRawString(3)) //TYPE_RESSOURCE
        );
    }
}