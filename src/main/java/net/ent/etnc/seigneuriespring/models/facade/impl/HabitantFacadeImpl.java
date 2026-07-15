package net.ent.etnc.seigneuriespring.models.facade.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.ent.etnc.seigneuriespring.models.entity.Habitant;
import net.ent.etnc.seigneuriespring.models.facade.HabitantFacade;
import net.ent.etnc.seigneuriespring.models.facade.exception.FacadeMetierException;
import net.ent.etnc.seigneuriespring.models.services.commun.exceptions.ServiceException;
import net.ent.etnc.seigneuriespring.models.services.crud.HabitantService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitantFacadeImpl implements HabitantFacade {

    @NonNull
    private final HabitantService habitantService;

    @Override
    public List<Habitant> recupererLesHabitants() throws FacadeMetierException {
        try {
            return habitantService.getAll();
        } catch (ServiceException e) {
            throw new FacadeMetierException(e.getMessage());
        }
    }
}