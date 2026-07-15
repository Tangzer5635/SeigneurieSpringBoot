package net.ent.etnc.seigneuriespring.models.facade.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.ent.etnc.seigneuriespring.models.entity.Evenement;
import net.ent.etnc.seigneuriespring.models.facade.EvenementFacade;
import net.ent.etnc.seigneuriespring.models.facade.exception.FacadeMetierException;
import net.ent.etnc.seigneuriespring.models.services.commun.exceptions.ServiceException;
import net.ent.etnc.seigneuriespring.models.services.crud.EvenementService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EvenementFacadeImpl implements EvenementFacade {

    @NonNull
    private final EvenementService evenementService;
    
    @Override
    public void finEvenement(Long idEvenement) throws FacadeMetierException {
        try {
            Evenement evenement = evenementService.findById(idEvenement);

            evenement.setDateFin(LocalDateTime.now());
            evenementService.save(evenement);
        } catch (ServiceException e) {
            throw new FacadeMetierException(e.getMessage(), e);
        }
    }

}
