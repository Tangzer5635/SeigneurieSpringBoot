package net.ent.etnc.seigneuriespring.models.facade;

import net.ent.etnc.seigneuriespring.models.entity.Seigneurie;
import net.ent.etnc.seigneuriespring.models.facade.dto.SeigneurieDTO;
import net.ent.etnc.seigneuriespring.models.facade.exception.FacadeMetierException;
import net.ent.etnc.seigneuriespring.models.services.commun.exceptions.ServiceException;

public interface SeigneurieFacade {
    Seigneurie creerSeigneurie(SeigneurieDTO seigneurieDTO) throws FacadeMetierException, ServiceException;
}
