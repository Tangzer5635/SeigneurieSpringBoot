package net.ent.etnc.seigneuriespring.batchs.seigneurie;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.ent.etnc.seigneuriespring.models.entity.EntitiesFactory;
import net.ent.etnc.seigneuriespring.models.entity.Habitant;
import net.ent.etnc.seigneuriespring.models.entity.Seigneurie;
import net.ent.etnc.seigneuriespring.models.services.crud.HabitantService;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SeigneurieProcessor implements ItemProcessor<SeigneurieLineCSV, Seigneurie> {

    @NonNull
    private final HabitantService habitantService;

    @Override
    public @Nullable Seigneurie process(SeigneurieLineCSV item) throws Exception {
        Habitant seigneur = habitantService.findById(item.seigneurId());

        return EntitiesFactory.creerSeigneurie(
                item.nom(),
                seigneur
        );
    }
}