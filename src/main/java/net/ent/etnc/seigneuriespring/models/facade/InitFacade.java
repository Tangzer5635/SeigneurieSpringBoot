package net.ent.etnc.seigneuriespring.models.facade;

import net.ent.etnc.seigneuriespring.models.facade.exception.FacadeMetierException;

public interface InitFacade {

    void initialiserDonnees() throws FacadeMetierException;
}
