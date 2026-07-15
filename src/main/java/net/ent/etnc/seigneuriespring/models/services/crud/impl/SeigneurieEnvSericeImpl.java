package net.ent.etnc.seigneuriespring.models.services.crud.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.ent.etnc.seigneuriespring.models.entity.Evenement;
import net.ent.etnc.seigneuriespring.models.entity.Seigneurie;
import net.ent.etnc.seigneuriespring.models.services.commun.exceptions.ServiceException;
import net.ent.etnc.seigneuriespring.models.services.crud.EvenementService;
import net.ent.etnc.seigneuriespring.models.services.crud.SeigneurieEnvService;
import net.ent.etnc.seigneuriespring.models.services.crud.SeigneurieService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeigneurieEnvSericeImpl implements SeigneurieEnvService {
    @NonNull
    private final SeigneurieService seigneurieService;

    @NonNull
    private final EvenementService evenementService;

    @Override
    public Seigneurie creerEvenementSeigneurie(Long idEvenement, Long idSeigneurie) throws ServiceException {
        try {
            Seigneurie seigneurie = seigneurieService.findByIdFetchEvenement(idSeigneurie);
            Evenement evenement = evenementService.findById(idEvenement);
            seigneurie.addEvenement(evenement);
            return seigneurieService.save(seigneurie);
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
