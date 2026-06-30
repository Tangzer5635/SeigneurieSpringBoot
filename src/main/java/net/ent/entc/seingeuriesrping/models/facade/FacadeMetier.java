package net.ent.entc.seingeuriesrping.models.facade;

import net.ent.entc.seingeuriesrping.models.entity.*;
import net.ent.entc.seingeuriesrping.models.entity.DTO.BatimentDTO;
import net.ent.entc.seingeuriesrping.models.entity.DTO.EvenementDTO;
import net.ent.entc.seingeuriesrping.models.entity.DTO.SeigneurieDTO;

import java.util.List;
import java.util.Map;

public interface FacadeMetier {

    //Créer une seigneurie
     Seigneurie creerSeigneurie(SeigneurieDTO seigneurieDTO);

    //Générer des ressources
     void ajouterRessourcesSeigneurie(Map<Ressource, Integer> ressources, Long idSeigneurie);

    //Renouveler la population d'une seigneurie
     Seigneurie renouvelerPopulationSeigneurie(Long idSeigneurie);

    //Créer un événement
     Seigneurie creerEvenementSeigneurie(Long idEvenement, Long idSeigneurie);

    //Mettre fin à un événement
     void finEvenement(Long idEvenement);

    //Construire un bâtiment dans une seigneurie
     Seigneurie creerBatimentSeigneurie(BatimentDTO batiment, Long idSeigneurie);

    //Détruire un bâtiment
     Seigneurie detruireBatimentSeigneurie(Long idBatiment, Long idSeigneurie);

    //Commercer des ressources entre 2 seigneuries
     void echangerRessourcesEntreSeigneuries(Long idSeigneurieEmettrice, Long idSeigneurieReceptrice, Map<Ressource, Integer> ressources);

     //Faire migrer des habitants entre 2 seigneuries
     void migrationHabitantEntreSeigneuries(Long idSeigneurieEmettrice, Long idSeigneurieReceptrice,Habitant[] habitants);

     //Ajouter un evenement
     Evenement creerEvenement(EvenementDTO dto);

     //Lister les seigneuries
     List<Seigneurie> listerSeigneuries();

     //Lister les evenements
     List<Evenement> listerEvenements();
 }
