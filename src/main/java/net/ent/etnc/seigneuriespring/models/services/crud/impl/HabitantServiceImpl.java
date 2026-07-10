package net.ent.etnc.seigneuriespring.models.services.crud.impl;

import net.ent.etnc.seigneuriespring.models.entity.Habitant;
import net.ent.etnc.seigneuriespring.models.repositories.HabitantRepository;
import net.ent.etnc.seigneuriespring.models.services.commun.CRUDServiceImpl;
import net.ent.etnc.seigneuriespring.models.services.crud.HabitantService;
import org.springframework.stereotype.Service;

@Service
public class HabitantServiceImpl extends CRUDServiceImpl<Habitant, HabitantRepository> implements HabitantService {

    public HabitantServiceImpl(HabitantRepository habitantRepository) {
        this.monrepo = habitantRepository;
    }
}