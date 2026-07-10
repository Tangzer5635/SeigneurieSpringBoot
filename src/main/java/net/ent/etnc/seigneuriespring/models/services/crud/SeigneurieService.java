package net.ent.etnc.seigneuriespring.models.services.crud;

import net.ent.etnc.seigneuriespring.models.entity.Habitant;
import net.ent.etnc.seigneuriespring.models.entity.Seigneurie;
import net.ent.etnc.seigneuriespring.models.services.commun.CRUDService;

import java.util.List;
import java.util.Optional;

public interface SeigneurieService extends CRUDService<Seigneurie> {

    List<Habitant> recupererLesHabitantsLesPlusVieux(Seigneurie seigneurie, int nbPersonne);

    Optional<Seigneurie> findByIdFetchBatiment(long l);

    Optional<Seigneurie> findByIdFetchEvenement(long l);
}
