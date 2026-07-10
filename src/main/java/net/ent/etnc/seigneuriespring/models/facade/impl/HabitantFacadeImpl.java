package net.ent.etnc.seigneuriespring.models.facade.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.ent.etnc.seigneuriespring.models.entity.EntitiesFactory;
import net.ent.etnc.seigneuriespring.models.entity.Habitant;
import net.ent.etnc.seigneuriespring.models.entity.Seigneurie;
import net.ent.etnc.seigneuriespring.models.entity.communs.exceptions.ValidException;
import net.ent.etnc.seigneuriespring.models.facade.HabitantFacade;
import net.ent.etnc.seigneuriespring.models.facade.exception.FacadeMetierException;
import net.ent.etnc.seigneuriespring.models.referencies.ConstanteMetier;
import net.ent.etnc.seigneuriespring.models.referencies.StatutHabitant;
import net.ent.etnc.seigneuriespring.models.services.commun.exceptions.ServiceException;
import net.ent.etnc.seigneuriespring.models.services.crud.HabitantService;
import net.ent.etnc.seigneuriespring.models.services.crud.SeigneurieService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class HabitantFacadeImpl implements HabitantFacade {

    @NonNull
    private final SeigneurieService seigneurieService;

    @NonNull
    private final HabitantService habitantService;

    @Override
    public Seigneurie renouvelerPopulationSeigneurie(Long idSeigneurie) throws FacadeMetierException, ServiceException {
        Seigneurie seigneurie = seigneurieService.findById(idSeigneurie);

        Set<Habitant> lesHabitantSeigneurie = seigneurie.getHabitants();
        if (lesHabitantSeigneurie.isEmpty()) {
            throw new FacadeMetierException("Il n'y a plus d'habitant dans la seigneurie");
        }
        if (lesHabitantSeigneurie.size() <= ConstanteMetier.NB_HABITANT_MINI) {
            throw new FacadeMetierException("Il n'y a pas assez d'habitant");
        }

        Random r = new Random();

        // Nombre de personnes à supprimer aléatoirement
        int nbPersonne = r.nextInt(lesHabitantSeigneurie.size());

        // Récupérer et supprimer les n plus vieux de la population
        List<Habitant> habitantSelectionne = seigneurieService.recupererLesHabitantsLesPlusVieux(seigneurie, nbPersonne);
        seigneurie.getHabitants().removeAll(habitantSelectionne);

        for (Habitant h : habitantSelectionne) {
            habitantService.deleteById(h.getId());
        }

        try {
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
        } catch (ValidException e) {
            throw new FacadeMetierException(e.affichageConstraintViolation(), e);
        }

        return seigneurieService.save(seigneurie);
    }

    @Override
    public void migrationHabitantEntreSeigneurie(Long idSeigneurieEmettrice, Long idSeigneurieReceptrice, Long[] habitants) throws FacadeMetierException, ServiceException {
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
    }
}
