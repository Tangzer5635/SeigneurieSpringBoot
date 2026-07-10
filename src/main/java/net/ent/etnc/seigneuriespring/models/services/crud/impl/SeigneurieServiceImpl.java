package net.ent.etnc.seigneuriespring.models.services.crud.impl;

import net.ent.etnc.seigneuriespring.models.entity.Habitant;
import net.ent.etnc.seigneuriespring.models.entity.Seigneurie;
import net.ent.etnc.seigneuriespring.models.repositories.SeigneurieRepository;
import net.ent.etnc.seigneuriespring.models.services.commun.CRUDServiceImpl;
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
    public Optional<Seigneurie> findByIdFetchBatiment(long l) {
        return this.monrepo.findByIdFetchBatiment(l);
    }

    @Override
    public Optional<Seigneurie> findByIdFetchEvenement(long l) {
        return this.monrepo.findByIdFetchEvenement(l);
    }
}
