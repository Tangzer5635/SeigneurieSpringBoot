package net.ent.etnc.seigneuriespring.batchs.ressource;

import net.ent.etnc.seigneuriespring.models.entity.EntitiesFactory;
import net.ent.etnc.seigneuriespring.models.entity.Ressource;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class RessourceProcessor implements ItemProcessor<RessourceLineCSV, Ressource> {

    @Override
    public @Nullable Ressource process(RessourceLineCSV item) throws Exception {
        return EntitiesFactory.creerRessource(
                item.nom(),
                item.type()
        );
    }
}