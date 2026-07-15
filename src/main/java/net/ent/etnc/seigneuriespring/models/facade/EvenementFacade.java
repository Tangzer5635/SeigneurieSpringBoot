package net.ent.etnc.seigneuriespring.models.facade;

import net.ent.etnc.seigneuriespring.models.facade.exception.FacadeMetierException;

public interface EvenementFacade {

    void finEvenement(Long idEvenement) throws FacadeMetierException;
}
