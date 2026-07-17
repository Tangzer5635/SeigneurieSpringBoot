package net.ent.etnc.seigneuriespring.batchs.habitant;

import net.ent.etnc.seigneuriespring.models.entity.EntitiesFactory;
import net.ent.etnc.seigneuriespring.models.entity.Habitant;
import net.ent.etnc.seigneuriespring.models.entity.vobjects.Nom;
import net.ent.etnc.seigneuriespring.models.entity.vobjects.Prenom;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class HabitantProcessor implements ItemProcessor<HabitantLineCSV, Habitant> {

    @Override
    public @Nullable Habitant process(HabitantLineCSV item) throws Exception {
//        if (item.nom() == null || item.prenom() == null) return null; SI NULL, LA LIGNE EST IGNOREE

        return EntitiesFactory.creerHabitant(
                new Nom(item.nom()),
                new Prenom(item.prenom()),
                item.date_naissance(),
                item.statue()
        );
    }
}
