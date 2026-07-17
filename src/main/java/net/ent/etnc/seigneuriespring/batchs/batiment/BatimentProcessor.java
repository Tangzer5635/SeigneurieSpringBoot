package net.ent.etnc.seigneuriespring.batchs.batiment;

import net.ent.etnc.seigneuriespring.models.entity.Batiment;
import net.ent.etnc.seigneuriespring.models.entity.EntitiesFactory;
import net.ent.etnc.seigneuriespring.models.entity.vobjects.Nom;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class BatimentProcessor implements ItemProcessor<BatimentLineCSV, Batiment> {

    @Override
    public @Nullable Batiment process(BatimentLineCSV item) throws Exception {
        return EntitiesFactory.creerBatiment(
                new Nom(item.nom()),
                item.est_actif(),
                item.typeBat()
        );
    }
}
