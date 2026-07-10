package net.ent.etnc.seigneuriespring.models.services.crud;

import net.ent.etnc.seigneuriespring.models.entity.Batiment;
import net.ent.etnc.seigneuriespring.models.services.commun.CRUDService;

public interface BatimentService extends CRUDService<Batiment> {

    boolean existsByNom(String nom);
}
