package net.ent.etnc.seigneuriespring.models.services.crud.impl;

import net.ent.etnc.seigneuriespring.models.entity.Ressource;
import net.ent.etnc.seigneuriespring.models.repositories.RessourceRepository;
import net.ent.etnc.seigneuriespring.models.services.commun.CRUDServiceImpl;
import net.ent.etnc.seigneuriespring.models.services.crud.RessourceService;
import org.springframework.stereotype.Service;

@Service
public class RessourceServiceImpl extends CRUDServiceImpl<Ressource, RessourceRepository> implements RessourceService {

    public RessourceServiceImpl(RessourceRepository ressourceRepository) {
        this.monrepo = ressourceRepository;
    }
}
