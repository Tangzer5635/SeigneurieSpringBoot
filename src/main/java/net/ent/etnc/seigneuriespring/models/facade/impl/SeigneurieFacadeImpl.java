package net.ent.etnc.seigneuriespring.models.facade.impl;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.ent.etnc.seigneuriespring.models.entity.*;
import net.ent.etnc.seigneuriespring.models.entity.communs.exceptions.ValidException;
import net.ent.etnc.seigneuriespring.models.facade.SeigneurieFacade;
import net.ent.etnc.seigneuriespring.models.facade.dto.BatimentDTO;
import net.ent.etnc.seigneuriespring.models.facade.dto.SeigneurieDTO;
import net.ent.etnc.seigneuriespring.models.facade.exception.FacadeMetierException;
import net.ent.etnc.seigneuriespring.models.referencies.ConstanteMetier;
import net.ent.etnc.seigneuriespring.models.referencies.StatutHabitant;
import net.ent.etnc.seigneuriespring.models.referencies.TypeBat;
import net.ent.etnc.seigneuriespring.models.services.commun.exceptions.ServiceException;
import net.ent.etnc.seigneuriespring.models.services.crud.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SeigneurieFacadeImpl implements SeigneurieFacade {

    @NonNull
    private final SeigneurieService seigneurieService;

    @NonNull
    private final BatimentService batimentService;

    @NonNull
    private final EvenementService evenementService;

    @NonNull
    private final HabitantService habitantService;

    @NonNull
    private final RessourceService ressourceService;

    @NonNull
    private final SeigneurieEnvService seigneurieEnvService;

    @Override
    public Seigneurie creerSeigneurie(SeigneurieDTO seigneurieDTO) throws FacadeMetierException {
        try {
            Habitant seigneur = habitantService.findById(seigneurieDTO.idSeigneur());
            Seigneurie seigneurie = EntitiesFactory.creerSeigneurie(seigneurieDTO.nom(), seigneur);
            return seigneurieService.save(seigneurie);
        } catch (ValidException | ServiceException e) {
            throw new FacadeMetierException(e.getMessage(), e);
        }
    }

    /**
     * ÉVÉNEMENTS
     */


    @Override
    public Seigneurie creerEvenementSeigneurie(Long idEvenement, Long idSeigneurie) throws FacadeMetierException {
        try {
            Seigneurie seigneurie = seigneurieService.findById(idSeigneurie);
            seigneurieEnvService.creerEvenementSeigneurie(idEvenement, idSeigneurie);
            return seigneurieService.save(seigneurie);
        } catch (ServiceException e) {
            throw new FacadeMetierException(e.getMessage(), e);
        }
    }

    /**
     * BÂTIMENTS
     */


    @Transactional(rollbackOn = Exception.class)
    @Override
    public Seigneurie creerBatimentSeigneurie(BatimentDTO batimentDTO, Long idSeigneurie) throws FacadeMetierException {
        try {
            Seigneurie seigneurie = seigneurieService.findById(idSeigneurie);

            if (batimentService.existsByNom(batimentDTO.nom())) {
                throw new FacadeMetierException("Impossible d'ajouter un batiment existant");
            }

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
        } catch (ValidException | ServiceException e) {
            throw new FacadeMetierException(e.getMessage(), e);
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Seigneurie detruireBatimentSeigneurie(Long idBatiment, Long idSeigneurie) throws FacadeMetierException {
        try {
            Seigneurie seigneurie = seigneurieService.findById(idSeigneurie);
            Batiment batiment = batimentService.findById(idBatiment);

            if (!seigneurie.getBatiments().contains(batiment)) {
                throw new FacadeMetierException("Le bâtiment n'appartient pas à cette seigneurie");
            }

            batiment.setEstActif(false);
            batimentService.deleteById(idBatiment);

            return seigneurieService.save(seigneurie);
        } catch (ServiceException e) {
            throw new FacadeMetierException(e.getMessage(), e);
        }
    }

    /**
     * HABITANTS
     */

    @Override
    public Seigneurie renouvelerPopulationSeigneurie(Long idSeigneurie) throws FacadeMetierException {
        try {
            Seigneurie seigneurie = seigneurieService.findById(idSeigneurie);

            Set<Habitant> lesHabitantSeigneurie = seigneurie.getHabitants();
            if (lesHabitantSeigneurie.isEmpty())
                throw new FacadeMetierException("Il n'y a plus d'habitant dans la seigneurie");
            if (lesHabitantSeigneurie.size() <= ConstanteMetier.NB_HABITANT_MINI)
                throw new FacadeMetierException("Il n'y a pas assez d'habitant");

            Random r = new Random();

            // Nombre de personnes à supprimer aléatoirement
            int nbPersonne = r.nextInt(lesHabitantSeigneurie.size());

            // Récupérer et supprimer les n plus vieux de la population
            List<Habitant> habitantSelectionne = seigneurieService.recupererLesHabitantsLesPlusVieux(seigneurie, nbPersonne);
            seigneurie.getHabitants().removeAll(habitantSelectionne);

            for (Habitant h : habitantSelectionne) {
                habitantService.deleteById(h.getId());
            }


            // Ajouter le double de n nouveaux jeunes habitants
            for (int i = 0; i < nbPersonne * 2; i++) {
                Habitant h = EntitiesFactory.creerHabitant(
                        ConstanteMetier.NOM_HABITANT_POSSIBLE[r.nextInt(10)],
                        ConstanteMetier.PRENOM_HABITANT_POSSIBLE[r.nextInt(12)],
                        LocalDate.of(Year.now().getValue() - 1, r.nextInt(12) + 1, r.nextInt(28) + 1),
                        StatutHabitant.PAYSAN);
                h = habitantService.save(h);
                seigneurie.addHabitant(h);
            }


            return seigneurieService.save(seigneurie);
        } catch (ValidException | ServiceException e) {
            throw new FacadeMetierException(e.getMessage(), e);
        }
    }

    @Override
    public void migrationHabitantEntreSeigneurie(Long idSeigneurieEmettrice, Long idSeigneurieReceptrice, Long[] habitants) throws FacadeMetierException {
        try {
            Seigneurie seigneurieEmettrice = seigneurieService.findById(idSeigneurieEmettrice);
            Seigneurie seigneurieReceptrice = seigneurieService.findById(idSeigneurieReceptrice);

            for (Long idHabitant : habitants) {
                Habitant habitant = habitantService.findById(idHabitant);

                // Vérifier qu'il appartient bien à la seigneurie émettrice
                if (!seigneurieEmettrice.getHabitants().contains(habitant)) {
                    throw new FacadeMetierException("L'habitant n'appartient pas à la seigneurie emettrice");
                }

                // Retirer de l'émettrice
                seigneurieEmettrice.removeHabitant(habitant);

                // Ajouter à la receptrice
                seigneurieReceptrice.addHabitant(habitant);
            }

            seigneurieService.save(seigneurieEmettrice);
            seigneurieService.save(seigneurieReceptrice);
        } catch (ServiceException e) {
            throw new FacadeMetierException(e.getMessage(), e);
        }
    }

    /**
     * RESSOURCES
     */

    @Override
    public void ajouterRessourcesSeigneurie(Long[][] ressources, Long idSeigneurie) throws FacadeMetierException {
        try {
            Seigneurie seigneurie = seigneurieService.findById(idSeigneurie);

            for (Long[] ressource : ressources) {
                Ressource res = ressourceService.findById(ressource[0]);

                seigneurie.addRessource(res, Math.toIntExact(ressource[1]));

            }
            seigneurieService.save(seigneurie);
        } catch (ServiceException e) {
            throw new FacadeMetierException(e.getMessage(), e);
        }
    }

    @Override
    public void echangeRessourcesEntreSeigneurie(Long idSeigneurieEmettrice, Long idSeigneurieReceptrice, Long[][] ressources) throws FacadeMetierException {
        try {
            Seigneurie seigneurieEmettrice = seigneurieService.findById(idSeigneurieEmettrice);
            Seigneurie seigneurieReceptrice = seigneurieService.findById(idSeigneurieReceptrice);

            for (Long[] ressourceData : ressources) {
                Long idRessource = ressourceData[0];
                int quantite = Math.toIntExact(ressourceData[1]);

                Ressource r = ressourceService.findById(idRessource);


                Integer quantitePossedee = seigneurieEmettrice.getRessources().get(r);
                if (quantitePossedee == null || quantitePossedee < quantite) {
                    throw new FacadeMetierException("La ressource n'est pas assez disponible");
                }

                seigneurieEmettrice.removeRessource(r, quantite);
                seigneurieReceptrice.addRessource(r, quantite);


            }
            seigneurieService.save(seigneurieEmettrice);
            seigneurieService.save(seigneurieReceptrice);
        } catch (ArithmeticException | ServiceException e) {
            throw new FacadeMetierException("Erreur conversion quantité ressource", e);
        }
    }

}
