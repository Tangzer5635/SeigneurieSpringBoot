package net.ent.etnc.seigneuriespring.models.services.crud;

import net.ent.etnc.seigneuriespring.models.entity.Habitant;
import net.ent.etnc.seigneuriespring.models.entity.Seigneurie;
import net.ent.etnc.seigneuriespring.models.services.commun.CRUDService;
import net.ent.etnc.seigneuriespring.models.services.commun.exceptions.ServiceException;

import java.util.List;

public interface SeigneurieService extends CRUDService<Seigneurie> {

    List<Habitant> recupererLesHabitantsLesPlusVieux(Seigneurie seigneurie, int nbPersonne);

    Seigneurie findByIdFetchBatiment(long l) throws ServiceException;

    Seigneurie findByIdFetchEvenement(long l) throws ServiceException;
}
