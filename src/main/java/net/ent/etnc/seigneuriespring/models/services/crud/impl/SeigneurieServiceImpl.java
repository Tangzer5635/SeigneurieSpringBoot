package net.ent.etnc.seigneuriespring.models.services.crud.impl;

import net.ent.etnc.seigneuriespring.models.entity.Habitant;
import net.ent.etnc.seigneuriespring.models.entity.Seigneurie;
import net.ent.etnc.seigneuriespring.models.repositories.SeigneurieRepository;
import net.ent.etnc.seigneuriespring.models.services.commun.CRUDServiceImpl;
import net.ent.etnc.seigneuriespring.models.services.commun.exceptions.ServiceException;
import net.ent.etnc.seigneuriespring.models.services.crud.SeigneurieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeigneurieServiceImpl extends CRUDServiceImpl<Seigneurie, SeigneurieRepository> implements SeigneurieService {
    public SeigneurieServiceImpl(SeigneurieRepository seigneurieRepository) {
        this.monrepo = seigneurieRepository;
    }

    @Override
    public List<Habitant> recupererLesHabitantsLesPlusVieux(Seigneurie seigneurie, int nbPersonne) {
        return this.monrepo.recupererLesHabitantsLesPlusVieux(seigneurie, nbPersonne);
    }

    @Override
    public Seigneurie findByIdFetchBatiment(long id) throws ServiceException {
        Optional<Seigneurie> optional = this.monrepo.findByIdFetchBatiment(id);
        if (optional.isEmpty()) throw new ServiceException("Le seigneurie n'existe pas");
        return optional.get();
    }

    @Override
    public Seigneurie findByIdFetchEvenement(long id) throws ServiceException {
        Optional<Seigneurie> optional = this.monrepo.findByIdFetchEvenement(id);
        if (optional.isEmpty()) throw new ServiceException("Le seigneurie n'existe pas");
        return optional.get();

    }
}
