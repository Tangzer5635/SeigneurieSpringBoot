package net.ent.etnc.seigneuriespring.models.services.crud;

import net.ent.etnc.seigneuriespring.models.entity.Seigneurie;
import net.ent.etnc.seigneuriespring.models.services.commun.exceptions.ServiceException;

public interface SeigneurieEnvService {

    Seigneurie creerEvenementSeigneurie(Long idEvenement, Long idSeigneurie) throws ServiceException;
}
