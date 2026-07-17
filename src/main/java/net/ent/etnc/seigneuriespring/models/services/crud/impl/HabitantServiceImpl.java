package net.ent.etnc.seigneuriespring.models.services.crud.impl;

import net.ent.etnc.seigneuriespring.models.entity.Habitant;
import net.ent.etnc.seigneuriespring.models.repositories.HabitantRepository;
import net.ent.etnc.seigneuriespring.models.services.commun.CRUDServiceImpl;
import net.ent.etnc.seigneuriespring.models.services.commun.exceptions.ServiceException;
import net.ent.etnc.seigneuriespring.models.services.crud.HabitantService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HabitantServiceImpl extends CRUDServiceImpl<Habitant, HabitantRepository> implements HabitantService {

    public HabitantServiceImpl(HabitantRepository habitantRepository) {
        this.monrepo = habitantRepository;
    }

    @Override
    public Habitant getByID(Long habitantId) throws ServiceException {
        Optional<Habitant> habitant = monrepo.findById(habitantId);
        if (habitant.isEmpty()) throw new ServiceException("L'habitant n'existe pas");
        return habitant.get();
    }
}