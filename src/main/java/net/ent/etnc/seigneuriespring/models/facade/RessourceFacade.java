package net.ent.etnc.seigneuriespring.models.facade;

import net.ent.etnc.seigneuriespring.models.facade.exception.FacadeMetierException;
import net.ent.etnc.seigneuriespring.models.services.commun.exceptions.ServiceException;

public interface RessourceFacade {

    void ajouterRessourcesSeigneurie(Long[][] ressources, Long idSeigneurie) throws FacadeMetierException, ServiceException;

    void echangeRessourcesEntreSeigneurie(Long idSeigneurieEmettrice, Long idSeigneurieReceptrice, Long[][] ressources) throws FacadeMetierException, ServiceException;
}
