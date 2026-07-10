package net.ent.etnc.seigneuriespring.models.facade.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.ent.etnc.seigneuriespring.models.entity.EntitiesFactory;
import net.ent.etnc.seigneuriespring.models.entity.Habitant;
import net.ent.etnc.seigneuriespring.models.entity.Seigneurie;
import net.ent.etnc.seigneuriespring.models.entity.communs.exceptions.ValidException;
import net.ent.etnc.seigneuriespring.models.facade.SeigneurieFacade;
import net.ent.etnc.seigneuriespring.models.facade.dto.SeigneurieDTO;
import net.ent.etnc.seigneuriespring.models.facade.exception.FacadeMetierException;
import net.ent.etnc.seigneuriespring.models.services.commun.exceptions.ServiceException;
import net.ent.etnc.seigneuriespring.models.services.crud.HabitantService;
import net.ent.etnc.seigneuriespring.models.services.crud.SeigneurieService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeigneurieFacadeImpl implements SeigneurieFacade {

    @NonNull
    private final SeigneurieService seigneurieService;

    @NonNull
    private final HabitantService habitantService;

    @Override
    public Seigneurie creerSeigneurie(SeigneurieDTO seigneurieDTO) throws FacadeMetierException, ServiceException {
        try {
            Habitant seigneur = habitantService.findById(seigneurieDTO.idSeigneur());
            Seigneurie seigneurie = EntitiesFactory.creerSeigneurie(seigneurieDTO.nom(), seigneur);
            return seigneurieService.save(seigneurie);
        } catch (ValidException e) {
            throw new FacadeMetierException(e.affichageConstraintViolation());
        }
    }
}
