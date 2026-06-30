package net.ent.entc.seingeuriesrping.models.facade;

import jakarta.transaction.Transactional;
import net.ent.entc.seingeuriesrping.models.entity.*;
import net.ent.entc.seingeuriesrping.models.entity.DTO.BatimentDTO;
import net.ent.entc.seingeuriesrping.models.entity.DTO.EvenementDTO;
import net.ent.entc.seingeuriesrping.models.entity.DTO.SeigneurieDTO;
import net.ent.entc.seingeuriesrping.models.entity.communs.exceptions.ValidException;
import net.ent.entc.seingeuriesrping.models.referencies.PrenomEpoque;
import net.ent.entc.seingeuriesrping.models.referencies.StatutHabitant;
import net.ent.entc.seingeuriesrping.models.repositories.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class ModelImpl implements FacadeMetier {
    private final SeigneurieRepository seigneurieRepository;
    private final RessourceRepository ressourceRepository;
    private final HabitantRepository habitantRepository;
    private final BatimentRepository batimentRepository;
    private final EvenementRepository evenementRepository;

    public ModelImpl(SeigneurieRepository seigneurieRepository, RessourceRepository ressourceRepository, HabitantRepository habitantRepository, BatimentRepository batimentRepository, EvenementRepository evenementRepository) {
        this.seigneurieRepository = seigneurieRepository;
        this.ressourceRepository = ressourceRepository;
        this.habitantRepository = habitantRepository;
        this.batimentRepository = batimentRepository;
        this.evenementRepository = evenementRepository;
    }

    @Override
    @Transactional
    public Seigneurie creerSeigneurie(SeigneurieDTO seigneurieDTO) {
        Seigneurie seigneurie = null;
        try {
            Habitant seigneur = EntitiesFactory.creerHabitant(
                    seigneurieDTO.nomSeigneur(),
                    seigneurieDTO.prenomSeigneur(),
                    seigneurieDTO.dateNaissanceSeigneur(),
                    seigneurieDTO.statutSeigneur()
            );
            seigneur = habitantRepository.save(seigneur);

            seigneurie = EntitiesFactory.creerSeigneurie(seigneurieDTO.nom(), seigneur);
            seigneurie = seigneurieRepository.save(seigneurie);

        } catch (ValidException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return seigneurie;
    }
    @Override
    @Transactional
    public void ajouterRessourcesSeigneurie(Map<Ressource, Integer> ressources, Long idSeigneurie) {
        Seigneurie seigneurie = getSeigneurieById(idSeigneurie);

        for (Map.Entry<Ressource, Integer> entry : ressources.entrySet()) {
            seigneurie.addRessource(entry.getKey(), entry.getValue());
        }

        seigneurieRepository.save(seigneurie);
    }

    public Seigneurie getSeigneurieById(Long idSeigneurie) {
        return seigneurieRepository.findById(idSeigneurie)
                .orElseThrow(() -> new RuntimeException("Seigneurie introuvable avec l'id " + idSeigneurie));
    }

    @Override
    @Transactional
    public Seigneurie renouvelerPopulationSeigneurie(Long idSeigneurie) {
        Seigneurie seigneurie = getSeigneurieById(idSeigneurie);
        double tauxDeces = 0.10;
        double tauxNaissance = 0.05;
        Random random = new Random();
        for (Habitant habitant : new ArrayList<>(seigneurie.getHabitants())) {
            if (!habitant.equals(seigneurie.getSeigneur()) && random.nextDouble() < tauxDeces) {
                seigneurie.removeHabitant(habitant);
                habitantRepository.delete(habitant);
            }
        }

        int population = seigneurie.getHabitants().size();
        for (int i = 0; i < population; i++) {
            if (random.nextDouble() < tauxNaissance) {
                try {
                    PrenomEpoque[] prenoms = PrenomEpoque.values();
                    String prenomUnique = prenoms[random.nextInt(prenoms.length)].name();
                    Habitant nouveauNe = EntitiesFactory.creerHabitant(
                            seigneurie.getSeigneur().getNomHabitant(),
                            prenomUnique,
                            LocalDate.now(),
                            StatutHabitant.PAYSAN
                    );
                    nouveauNe = habitantRepository.save(nouveauNe);
                    seigneurie.addHabitant(nouveauNe);
                } catch (ValidException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
        }
        return seigneurieRepository.save(seigneurie);
    }

    @Override
    @Transactional
    public Seigneurie creerEvenementSeigneurie(Long idEvenement, Long idSeigneurie) {

        Seigneurie seigneurie = getSeigneurieById(idSeigneurie);

        Evenement evenement = evenementRepository.findById(idEvenement)
                .orElseThrow(() -> new RuntimeException("Événement introuvable"));

        seigneurie.addEvenement(evenement);

        return seigneurieRepository.save(seigneurie);
    }

    @Override
    @Transactional
    public void finEvenement(Long idEvenement) {
        Evenement evenement = evenementRepository.findById(idEvenement).orElseThrow(() -> new RuntimeException("Evenement introuvable"));
        evenement.setDateFin(LocalDateTime.now());
        evenementRepository.save(evenement);
    }

    @Override
    @Transactional
    public Seigneurie creerBatimentSeigneurie(BatimentDTO batimentDTO, Long idSeigneurie) {

        try {

            Seigneurie seigneurie = getSeigneurieById(idSeigneurie);

            Batiment batiment = EntitiesFactory.creerBatiment(
                    batimentDTO.nomBatiment(),
                    batimentDTO.estActif(),
                    batimentDTO.typeBatiment()
            );

            batiment = batimentRepository.save(batiment);

            seigneurie.addBatiment(batiment);

            return seigneurieRepository.save(seigneurie);

        } catch (ValidException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public Seigneurie detruireBatimentSeigneurie(Long idBatiment, Long idSeigneurie) {
        Seigneurie seigneurie = getSeigneurieById(idSeigneurie);
        Batiment batiment = batimentRepository.findById(idBatiment).orElseThrow(() -> new RuntimeException("Batiment introuvable"));
        seigneurie.removeBatiment(batiment);
        batimentRepository.delete(batiment);

        return seigneurieRepository.save(seigneurie);
    }

    @Override
    @Transactional
    public void echangerRessourcesEntreSeigneuries(Long idSeigneurieEmettrice, Long idSeigneurieReceptrice, Map<Ressource, Integer> ressources) {
        Seigneurie emettrice = getSeigneurieById(idSeigneurieEmettrice);
        Seigneurie receptrice = getSeigneurieById(idSeigneurieReceptrice);

        for (Map.Entry<Ressource, Integer> entry : ressources.entrySet()) {
            Ressource ressource = entry.getKey();
            Integer quantite = entry.getValue();
            emettrice.removeRessource(ressource, quantite);
            receptrice.addRessource(ressource, quantite);
        }

        seigneurieRepository.save(emettrice);
        seigneurieRepository.save(receptrice);
    }

    @Override
    @Transactional
    public void migrationHabitantEntreSeigneuries(Long idSeigneurieEmettrice, Long idSeigneurieReceptrice, Habitant[] habitants) {

        Seigneurie emettrice = getSeigneurieById(idSeigneurieEmettrice);
        Seigneurie receptrice = getSeigneurieById(idSeigneurieReceptrice);

        for (Habitant habitant : habitants) {
            if (!emettrice.getHabitants().contains(habitant)) {
                throw new RuntimeException( "L'habitant " + habitant.getPrenomHabitant() + " n'appartient pas à la seigneurie émettrice.");
            }
            if (habitant.getId().equals(emettrice.getSeigneur().getId())) { throw new RuntimeException("Le seigneur ne peut pas migrer.");}

            emettrice.removeHabitant(habitant);
            receptrice.addHabitant(habitant);
        }

        seigneurieRepository.save(emettrice);
        seigneurieRepository.save(receptrice);
    }

    @Override
    @Transactional
    public Evenement creerEvenement(EvenementDTO evenementDTO) {

        try {

            Evenement evenement = EntitiesFactory.creerEvenement(
                    evenementDTO.nomEvenement(),
                    evenementDTO.descriptionEvenement(),
                    evenementDTO.dateDebut(),
                    evenementDTO.dateFin(),
                    evenementDTO.typeEvenement()
            );

            return evenementRepository.save(evenement);

        } catch (ValidException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public List<Seigneurie> listerSeigneuries() {
        return seigneurieRepository.findAll();
    }

    @Override
    @Transactional
    public List<Evenement> listerEvenements() {
        return evenementRepository.findAll();
    }
}