package net.ent.entc.seingeuriesrping.presenter;

import lombok.RequiredArgsConstructor;
import net.ent.entc.seingeuriesrping.models.entity.DTO.BatimentDTO;
import net.ent.entc.seingeuriesrping.models.entity.DTO.EvenementDTO;
import net.ent.entc.seingeuriesrping.models.entity.DTO.SeigneurieDTO;
import net.ent.entc.seingeuriesrping.models.entity.Evenement;
import net.ent.entc.seingeuriesrping.models.entity.Habitant;
import net.ent.entc.seingeuriesrping.models.entity.Ressource;
import net.ent.entc.seingeuriesrping.models.entity.Seigneurie;
import net.ent.entc.seingeuriesrping.models.facade.FacadeMetier;
import net.ent.entc.seingeuriesrping.view.View;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class Presenter implements CommandLineRunner {

    private final FacadeMetier model;
    private final View view;

    @Override
    public void run(String... args) {

        boolean running = true;

        while (running) {

            int choix = view.afficherMenuPrincipal();

            switch (choix) {

                case 1 -> creerSeigneurie();

                case 2 -> creerBatiment();

                case 3 -> renouvelerPopulation();

                case 4 -> creerEvenement();

                case 5 -> lierEvenementSeigneurie();

//                case 6 -> terminerEvenement();
//
//                case 7 -> detruireBatiment();
//
//                case 8 -> ajouterRessources();
//
//                case 9 -> echangerRessources();
//
//                case 10 -> migrationHabitants();

                case 0 -> {
                    view.afficherMessage("Au revoir !");
                    running = false;
                }
                default -> view.afficherErreur("Choix invalide.");
            }
        }
    }

    private void creerSeigneurie() {
        try {
            SeigneurieDTO dto = view.saisirSeigneurie();
            model.creerSeigneurie(dto);
            view.afficherMessage("Seigneurie créée avec succès.");
        } catch (Exception e) {
            view.afficherErreur(e.getMessage());
        }
    }

    private void creerBatiment() {
        try {
            Seigneurie seigneurie = view.choisirSeigneurie(model.listerSeigneuries());
            BatimentDTO dto = view.saisirBatiment();
            model.creerBatimentSeigneurie(dto, seigneurie.getId());
            view.afficherMessage("Bâtiment créé.");
        } catch (Exception e) {
            view.afficherErreur(e.getMessage());
        }
    }

    private void renouvelerPopulation() {
        try {
            Seigneurie seigneurie = view.choisirSeigneurie(model.listerSeigneuries());
            model.renouvelerPopulationSeigneurie(seigneurie.getId());
            view.afficherMessage("Population renouvelée.");
        } catch (Exception e) {
            view.afficherErreur(e.getMessage());
        }
    }

    private void lierEvenementSeigneurie() {
        try {
            Seigneurie seigneurie = view.choisirSeigneurie(model.listerSeigneuries());
            Evenement evenement = view.choisirEvenement(model.listerEvenements());
            model.creerEvenementSeigneurie(evenement.getId(), seigneurie.getId());
            view.afficherMessage("Évènement ajouté à la seigneurie.");
        } catch (Exception e) {
            view.afficherErreur(e.getMessage());
        }
    }

    private void creerEvenement() {
        try {
            EvenementDTO dto = view.saisirEvenement();
            model.creerEvenement(dto);
            view.afficherMessage("Évènement créé avec succès.");
        } catch (Exception e) {
            view.afficherErreur(e.getMessage());
        }
    }
}