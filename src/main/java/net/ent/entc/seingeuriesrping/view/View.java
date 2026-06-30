package net.ent.entc.seingeuriesrping.view;

import net.ent.entc.seingeuriesrping.models.entity.DTO.BatimentDTO;
import net.ent.entc.seingeuriesrping.models.entity.DTO.EvenementDTO;
import net.ent.entc.seingeuriesrping.models.entity.DTO.SeigneurieDTO;
import net.ent.entc.seingeuriesrping.models.entity.Evenement;
import net.ent.entc.seingeuriesrping.models.entity.Habitant;
import net.ent.entc.seingeuriesrping.models.entity.Ressource;
import net.ent.entc.seingeuriesrping.models.entity.Seigneurie;

import java.util.List;
import java.util.Map;

public interface View {

    int afficherMenuPrincipal();

    void afficherMessage(String message);

    void afficherErreur(String message);

    Long saisirIdEvenement();

    Long saisirIdBatiment();

    SeigneurieDTO saisirSeigneurie();

    BatimentDTO saisirBatiment();

    Habitant[] saisirHabitants();

    Map<Ressource,Integer> saisirRessources();

    EvenementDTO saisirEvenement();

    Seigneurie choisirSeigneurie(List<Seigneurie> seigneuries);

    Evenement choisirEvenement(List<Evenement> evenements);
}