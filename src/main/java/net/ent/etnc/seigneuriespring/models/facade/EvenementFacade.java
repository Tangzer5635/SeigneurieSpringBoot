package net.ent.etnc.seigneuriespring.models.facade;

import net.ent.etnc.seigneuriespring.models.entity.Seigneurie;
import net.ent.etnc.seigneuriespring.models.facade.dto.BatimentDTO;
import net.ent.etnc.seigneuriespring.models.facade.exception.FacadeMetierException;
import net.ent.etnc.seigneuriespring.models.services.commun.exceptions.ServiceException;

public interface EvenementFacade {
    Seigneurie creerEvenementSeigneurie(Long idEvenement, Long idSeigneurie) throws FacadeMetierException, ServiceException;

    void finEvenement(Long idEvenement) throws FacadeMetierException, ServiceException;

    Seigneurie creerBatimentSeigneurie(BatimentDTO batimentDTO, Long idSeigneurie) throws FacadeMetierException, ServiceException;

    Seigneurie detruireBatimentSeigneurie(Long idBatiment, Long idSeigneurie) throws FacadeMetierException, ServiceException;
}
