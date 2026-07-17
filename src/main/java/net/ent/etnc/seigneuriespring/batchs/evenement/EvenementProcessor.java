package net.ent.etnc.seigneuriespring.batchs.evenement;

import net.ent.etnc.seigneuriespring.models.entity.EntitiesFactory;
import net.ent.etnc.seigneuriespring.models.entity.Evenement;
import net.ent.etnc.seigneuriespring.models.entity.vobjects.Nom;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class EvenementProcessor implements ItemProcessor<EvenementLineCSV, Evenement> {

    @Override
    public @Nullable Evenement process(EvenementLineCSV item) throws Exception {
        return EntitiesFactory.creerEvenement(
                new Nom(item.nom()),
                item.description(),
                item.date_debut(),
                item.date_fin(),
                item.type()
        );
    }
}