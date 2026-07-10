package net.ent.etnc.seigneuriespring.models.facade.impl;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.ent.etnc.seigneuriespring.models.entity.Batiment;
import net.ent.etnc.seigneuriespring.models.entity.EntitiesFactory;
import net.ent.etnc.seigneuriespring.models.entity.Evenement;
import net.ent.etnc.seigneuriespring.models.entity.Seigneurie;
import net.ent.etnc.seigneuriespring.models.entity.communs.exceptions.ValidException;
import net.ent.etnc.seigneuriespring.models.facade.EvenementFacade;
import net.ent.etnc.seigneuriespring.models.facade.dto.BatimentDTO;
import net.ent.etnc.seigneuriespring.models.facade.exception.FacadeMetierException;
import net.ent.etnc.seigneuriespring.models.referencies.TypeBat;
import net.ent.etnc.seigneuriespring.models.services.commun.exceptions.ServiceException;
import net.ent.etnc.seigneuriespring.models.services.crud.BatimentService;
import net.ent.etnc.seigneuriespring.models.services.crud.EvenementService;
import net.ent.etnc.seigneuriespring.models.services.crud.SeigneurieService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EvenementFacadeImpl implements EvenementFacade {

    @NonNull
    private final SeigneurieService seigneurieService;

    @NonNull
    private final EvenementService evenementService;

    @NonNull
    private final BatimentService batimentService;

    @Override
    public Seigneurie creerEvenementSeigneurie(Long idEvenement, Long idSeigneurie) throws FacadeMetierException, ServiceException {
        Seigneurie seigneurie = seigneurieService.findById(idSeigneurie);
        Evenement evenement = evenementService.findById(idEvenement);

        seigneurie.addEvenement(evenement);

        return seigneurieService.save(seigneurie);
    }

    @Override
    public void finEvenement(Long idEvenement) throws FacadeMetierException, ServiceException {
        Evenement evenement = evenementService.findById(idEvenement);

        evenement.setDateFin(LocalDateTime.now());
        evenementService.save(evenement);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Seigneurie creerBatimentSeigneurie(BatimentDTO batimentDTO, Long idSeigneurie) throws FacadeMetierException, ServiceException {
        Seigneurie seigneurie = seigneurieService.findById(idSeigneurie);

        if (batimentService.existsByNom(batimentDTO.nom())) {
            throw new FacadeMetierException("Impossible d'ajouter un batiment existant");
        }

        try {
            // 1. créer bâtiment avec DTO
            Batiment batiment = EntitiesFactory.creerBatiment(
                    batimentDTO.nom(),
                    batimentDTO.isActif(),
                    TypeBat.valueOf(batimentDTO.typeBatiment()));
            // 2. save batiment
            batiment = batimentService.save(batiment);
            // 3. ajouter à la seigneurie
            seigneurie.addBatiment(batiment);
            // 4. save seigneurie
            return seigneurieService.save(seigneurie);
        } catch (ValidException e) {
            throw new FacadeMetierException(e.affichageConstraintViolation());
        } catch (Exception e) {
            throw new FacadeMetierException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Seigneurie detruireBatimentSeigneurie(Long idBatiment, Long idSeigneurie) throws FacadeMetierException, ServiceException {
        Seigneurie seigneurie = seigneurieService.findById(idSeigneurie);
        Batiment batiment = batimentService.findById(idBatiment);

        if (!seigneurie.getBatiments().contains(batiment)) {
            throw new FacadeMetierException("Le bâtiment n'appartient pas à cette seigneurie");
        }

        batiment.setEstActif(false);
        batimentService.deleteById(idBatiment);

        return seigneurieService.save(seigneurie);
    }
}
