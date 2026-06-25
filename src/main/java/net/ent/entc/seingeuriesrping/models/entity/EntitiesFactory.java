package net.ent.entc.seingeuriesrping.models.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.entc.seingeuriesrping.models.entity.communs.ValidUtils;
import net.ent.entc.seingeuriesrping.models.entity.communs.exceptions.ValidException;
import net.ent.entc.seingeuriesrping.models.referencies.StatutHabitant;
import net.ent.entc.seingeuriesrping.models.referencies.TypeBat;
import net.ent.entc.seingeuriesrping.models.referencies.TypeEvenement;
import net.ent.entc.seingeuriesrping.models.referencies.TypeRessource;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {

    public static Batiment creerBatiment(String nom, boolean actif, TypeBat type) throws ValidException {
        Batiment b = new Batiment();
        b.setNomBatiment(nom);
        b.setEstActif(actif);
        b.setTypeBatiment(type);
        ValidUtils.validate(b);
        return b;
    }

    public static Evenement creerEvenement(String nom, String description, LocalDateTime dateDebut, LocalDateTime dateFin, TypeEvenement type) throws ValidException {
        Evenement e = new Evenement();
        e.setNomEvenement(nom);
        e.setDescriptionEvenement(description);
        e.setDateDebut(dateDebut);
        e.setDateFin(dateFin);
        e.setTypeEvenement(type);
        ValidUtils.validate(e);
        return e;
    }

    public static Habitant creerHabitant(String nom, String prenom, LocalDate dateNaissance, StatutHabitant statut) throws ValidException {
        Habitant h = new Habitant();
        h.setNomHabitant(nom);
        h.setPrenomHabitant(prenom);
        h.setDateNaissance(dateNaissance);
        h.setStatutHabitant(statut);
        ValidUtils.validate(h);
        return h;
    }

    public static Ressource creerRessource(String nom, TypeRessource type) throws ValidException {
        Ressource r = new Ressource();
        r.setNomRessource(nom);
        r.setTypeRessource(type);
        ValidUtils.validate(r);
        return r;
    }

    public static Seigneurie creerSeigneurie(String nom, Habitant seigneur) throws ValidException {
        Seigneurie s = new Seigneurie();
        s.setNom(nom);
        s.setSeigneur(seigneur);
        s.addHabitant(seigneur);
        ValidUtils.validate(s);
        return s;
    }
}
