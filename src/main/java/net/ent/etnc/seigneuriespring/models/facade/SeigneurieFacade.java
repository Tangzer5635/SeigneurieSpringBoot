package net.ent.etnc.seigneuriespring.models.facade;

import net.ent.etnc.seigneuriespring.models.entity.Seigneurie;
import net.ent.etnc.seigneuriespring.models.facade.dto.BatimentDTO;
import net.ent.etnc.seigneuriespring.models.facade.dto.SeigneurieDTO;
import net.ent.etnc.seigneuriespring.models.facade.exception.FacadeMetierException;

import java.util.List;

public interface SeigneurieFacade {
    Seigneurie creerSeigneurie(SeigneurieDTO seigneurieDTO) throws FacadeMetierException;

    Seigneurie creerEvenementSeigneurie(Long idEvenement, Long idSeigneurie) throws FacadeMetierException;

    Seigneurie creerBatimentSeigneurie(BatimentDTO batimentDTO, Long idSeigneurie) throws FacadeMetierException;

    Seigneurie detruireBatimentSeigneurie(Long idBatiment, Long idSeigneurie) throws FacadeMetierException;

    Seigneurie renouvelerPopulationSeigneurie(Long idSeigneurie) throws FacadeMetierException;

    void migrationHabitantEntreSeigneurie(Long idSeigneurieEmettrice, Long idSeigneurieReceptrice, Long[] habitants) throws FacadeMetierException;

    void ajouterRessourcesSeigneurie(Long[][] ressources, Long idSeigneurie) throws FacadeMetierException;

    void echangeRessourcesEntreSeigneurie(Long idSeigneurieEmettrice, Long idSeigneurieReceptrice, Long[][] ressources) throws FacadeMetierException;

    List<Seigneurie> recupererToutesLesSeigneuries() throws FacadeMetierException;
}
