package net.ent.etnc.seigneuriespring.models.entity;

import lombok.NoArgsConstructor;
import net.ent.etnc.seigneuriespring.models.entity.communs.ValidUtils;
import net.ent.etnc.seigneuriespring.models.entity.communs.exceptions.ValidException;
import net.ent.etnc.seigneuriespring.models.entity.vobjects.Nom;
import net.ent.etnc.seigneuriespring.models.entity.vobjects.Prenom;
import net.ent.etnc.seigneuriespring.models.referencies.StatutHabitant;
import net.ent.etnc.seigneuriespring.models.referencies.TypeBat;
import net.ent.etnc.seigneuriespring.models.referencies.TypeEvenement;
import net.ent.etnc.seigneuriespring.models.referencies.TypeRessource;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class EntitiesFactory {
    public static Batiment creerBatiment(Nom nom, boolean actif, TypeBat type) throws ValidException {
        Batiment b = new Batiment();
        b.setNom(nom);
        b.setEstActif(actif);
        b.setType(type);
        ValidUtils.validate(b);
        return b;
    }

    public static Evenement creerEvenement(Nom nom, String description, LocalDateTime dateDebut, LocalDateTime dateFin, TypeEvenement type) throws ValidException {
        Evenement e = new Evenement();
        e.setNom(nom);
        e.setDescription(description);
        e.setDateDebut(dateDebut);
        e.setDateFin(dateFin);
        e.setType(type);
        ValidUtils.validate(e);
        return e;
    }

    public static Habitant creerHabitant(Nom nom, Prenom prenom, LocalDate dateNaissance, StatutHabitant statut) throws ValidException {
        Habitant h = new Habitant();
        h.setNom(nom);
        h.setPrenom(prenom);
        h.setDateNaissance(dateNaissance);
        h.setStatut(statut);
        ValidUtils.validate(h);
        return h;
    }

    public static Ressource creerRessource(String nom, TypeRessource type) throws ValidException {
        Ressource r = new Ressource();
        r.setNom(nom);
        r.setType(type);
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
