package net.ent.etnc.seigneuriespring.models.services.crud.impl;

import net.ent.etnc.seigneuriespring.models.entity.Evenement;
import net.ent.etnc.seigneuriespring.models.repositories.EvenementRepository;
import net.ent.etnc.seigneuriespring.models.services.commun.CRUDServiceImpl;
import net.ent.etnc.seigneuriespring.models.services.crud.EvenementService;
import org.springframework.stereotype.Service;

@Service
public class EvenementServiceImpl extends CRUDServiceImpl<Evenement, EvenementRepository> implements EvenementService {

    public EvenementServiceImpl(EvenementRepository evenementRepository) {
        this.monrepo = evenementRepository;
    }
}