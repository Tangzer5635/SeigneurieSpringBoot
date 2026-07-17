package net.ent.etnc.seigneuriespring.models.services.crud.impl;

import net.ent.etnc.seigneuriespring.models.entity.Batiment;
import net.ent.etnc.seigneuriespring.models.repositories.BatimentRepository;
import net.ent.etnc.seigneuriespring.models.services.commun.CRUDServiceImpl;
import net.ent.etnc.seigneuriespring.models.services.crud.BatimentService;
import org.springframework.stereotype.Service;

@Service
public class BatimentServiceImpl extends CRUDServiceImpl<Batiment, BatimentRepository> implements BatimentService {

    public BatimentServiceImpl(BatimentRepository batimentRepository) {
        this.monrepo = batimentRepository;
    }

    @Override
    public boolean existsByNom(String nom) {
        return this.monrepo.existsByNom(nom);
    }
}