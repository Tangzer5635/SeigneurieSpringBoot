package net.ent.etnc.seigneuriespring.models.facade.impl;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.ent.etnc.seigneuriespring.models.entity.*;
import net.ent.etnc.seigneuriespring.models.entity.communs.exceptions.ValidException;
import net.ent.etnc.seigneuriespring.models.entity.vobjects.Nom;
import net.ent.etnc.seigneuriespring.models.entity.vobjects.Prenom;
import net.ent.etnc.seigneuriespring.models.facade.InitFacade;
import net.ent.etnc.seigneuriespring.models.facade.exception.FacadeMetierException;
import net.ent.etnc.seigneuriespring.models.referencies.StatutHabitant;
import net.ent.etnc.seigneuriespring.models.referencies.TypeBat;
import net.ent.etnc.seigneuriespring.models.referencies.TypeEvenement;
import net.ent.etnc.seigneuriespring.models.referencies.TypeRessource;
import net.ent.etnc.seigneuriespring.models.services.commun.exceptions.ServiceException;
import net.ent.etnc.seigneuriespring.models.services.crud.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class InitFacadeImpl implements InitFacade {

    @NonNull
    private final HabitantService habitantService;

    @NonNull
    private final RessourceService ressourceService;

    @NonNull
    private final EvenementService evenementService;

    @NonNull
    private final SeigneurieService seigneurieService;

    @NonNull
    private final BatimentService batimentService;

    @NonNull
    private final SeigneurieEnvService seigneurieEnvService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void initialiserDonnees() throws FacadeMetierException {
        try {
            initHabitant();
            initRessource();
            initEvenement();
            initSeigneurie();
            initBatiment();
            initEvtSeigneurie();

        } catch (IOException | URISyntaxException | ServiceException e) {
            throw new FacadeMetierException(e.getMessage(), e);
        } catch (ValidException e) {
            throw new FacadeMetierException(e.affichageConstraintViolation());
        }
    }

    /**
     * Charge un fichier CSV depuis les ressources
     */
    private List<String> chargementFichier(String path) throws IOException, URISyntaxException {
        Path pathFichier = Path.of(Objects.requireNonNull(InitFacade.class.getResource(path)).toURI());
        List<String> lignes = Files.readAllLines(pathFichier);
        if (!lignes.isEmpty()) {
            lignes.removeFirst(); // Supprimer l'en-tête
        }
        return lignes;
    }

    private void initHabitant() throws URISyntaxException, IOException, ValidException, ServiceException {
        List<String> lignes = chargementFichier("/csv/habitant.csv");
        for (String line : lignes) {
            String[] data = line.split(";");
            Habitant hab = EntitiesFactory.creerHabitant(
                    new Nom(data[2]),
                    new Prenom(data[3]),
                    LocalDate.parse(data[4]),
                    StatutHabitant.valueOf(data[5]));
            habitantService.save(hab);
        }
    }

    private void initRessource() throws URISyntaxException, IOException, ValidException, ServiceException {
        List<String> lignes = chargementFichier("/csv/ressource.csv");
        for (String line : lignes) {
            String[] data = line.split(";");
            Ressource ressource = EntitiesFactory.creerRessource(
                    data[2],
                    TypeRessource.valueOf(data[3]));
            ressourceService.save(ressource);
        }
    }

    private void initEvenement() throws URISyntaxException, IOException, ValidException, ServiceException {
        List<String> lignes = chargementFichier("/csv/evenement.csv");
        for (String line : lignes) {
            String[] data = line.split(";");
            Evenement evt = EntitiesFactory.creerEvenement(
                    new Nom(data[2]),
                    data[3],
                    LocalDateTime.parse(data[4]),
                    LocalDateTime.parse(data[5]),
                    TypeEvenement.valueOf(data[6]));
            evenementService.save(evt);
        }
    }

    private void initSeigneurie() throws URISyntaxException, IOException, ValidException, FacadeMetierException, ServiceException {
        List<String> lignes = chargementFichier("/csv/seigneurie.csv");
        for (String line : lignes) {
            String[] data = line.split(";");
            Habitant seigneur = habitantService.findById(Long.parseLong(data[3]));

            Seigneurie seigneurie = EntitiesFactory.creerSeigneurie(data[2], seigneur);
            seigneurieService.save(seigneurie);
        }
    }

    private void initBatiment() throws URISyntaxException, IOException, ValidException, FacadeMetierException, ServiceException {
        List<String> lignes = chargementFichier("/csv/batiment.csv");
        for (String line : lignes) {
            String[] data = line.split(";");
            Seigneurie seigneurie = seigneurieService.findByIdFetchBatiment(Long.parseLong(data[5]));

            Batiment batiment = EntitiesFactory.creerBatiment(
                    new Nom(data[2]),
                    Boolean.parseBoolean(data[3]),
                    TypeBat.valueOf(data[4]));

            batiment = batimentService.save(batiment);
            seigneurie.addBatiment(batiment);
            seigneurieService.save(seigneurie);
        }
    }

    private void initEvtSeigneurie() throws URISyntaxException, IOException, ValidException, FacadeMetierException, ServiceException {
        List<String> lignes = chargementFichier("/csv/seigneurie_evenement.csv");
        for (String line : lignes) {
            String[] data = line.split(";");
            this.seigneurieEnvService.creerEvenementSeigneurie(Long.parseLong(data[1]), Long.parseLong(data[0]));
        }
    }
}
