package net.ent.etnc.seigneuriespring.models.facade;

import net.ent.etnc.seigneuriespring.models.entity.Seigneurie;
import net.ent.etnc.seigneuriespring.models.facade.exception.FacadeMetierException;
import net.ent.etnc.seigneuriespring.models.services.commun.exceptions.ServiceException;

public interface HabitantFacade {
    Seigneurie renouvelerPopulationSeigneurie(Long idSeigneurie) throws FacadeMetierException, ServiceException;

    void migrationHabitantEntreSeigneurie(Long idSeigneurieEmettrice, Long idSeigneurieReceptrice, Long[] habitants) throws FacadeMetierException, ServiceException;
}
