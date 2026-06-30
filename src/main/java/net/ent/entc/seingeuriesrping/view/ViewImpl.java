package net.ent.entc.seingeuriesrping.view;

import net.ent.entc.seingeuriesrping.models.entity.DTO.BatimentDTO;
import net.ent.entc.seingeuriesrping.models.entity.DTO.EvenementDTO;
import net.ent.entc.seingeuriesrping.models.entity.DTO.SeigneurieDTO;
import net.ent.entc.seingeuriesrping.models.entity.Evenement;
import net.ent.entc.seingeuriesrping.models.entity.Habitant;
import net.ent.entc.seingeuriesrping.models.entity.Ressource;
import net.ent.entc.seingeuriesrping.models.entity.Seigneurie;
import net.ent.entc.seingeuriesrping.models.referencies.StatutHabitant;
import net.ent.entc.seingeuriesrping.models.referencies.TypeBat;
import net.ent.entc.seingeuriesrping.models.referencies.TypeEvenement;
import net.ent.entc.seingeuriesrping.view.utils.AffichageConsole;
import net.ent.entc.seingeuriesrping.view.utils.LectureConsole;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class ViewImpl implements View {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public int afficherMenuPrincipal() {

        Map<Integer, String> menu = new LinkedHashMap<>();

        menu.put(1, "Créer une seigneurie");
        menu.put(2, "Créer un bâtiment");
        menu.put(3, "Renouveler la population");
        menu.put(4, "Créer un évènement");
        menu.put(5, "Lier un évènement à une seigneurie");
        menu.put(0, "Quitter");

        AffichageConsole.afficherMenuEntoure(menu, "GESTION DES SEIGNEURIES");

        return LectureConsole.lectureChoixInt(0, 9);
    }

    @Override
    public void afficherMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void afficherErreur(String message) {
        System.err.println(message);
    }

    @Override
    public Seigneurie choisirSeigneurie(List<Seigneurie> seigneuries) {

        if (seigneuries.isEmpty()) {
            AffichageConsole.afficherErreur("Aucune seigneurie disponible.");
            return null;
        }

        Map<Integer, String> menu = new LinkedHashMap<>();

        int i = 1;
        for (Seigneurie s : seigneuries) {
            menu.put(i++, s.getNom());
        }

        AffichageConsole.afficherMenuEntoure(menu, "CHOIX DE LA SEIGNEURIE");

        int choix = LectureConsole.lectureChoixInt(1, seigneuries.size());

        return seigneuries.get(choix - 1);
    }

    @Override
    public Evenement choisirEvenement(List<Evenement> evenements) {
        if (evenements.isEmpty()) {
            AffichageConsole.afficherErreur("Aucun évènement disponible.");
            return null;
        }

        Map<Integer, String> menu = new LinkedHashMap<>();

        int i = 1;
        for (Evenement e : evenements) {
            menu.put(i++, e.getNomEvenement());
        }

        AffichageConsole.afficherMenuEntoure(menu, "CHOIX DE L'EVENEMENT");
        int choix = LectureConsole.lectureChoixInt(1, evenements.size());
        return evenements.get(choix - 1);
    }

    @Override
    public Long saisirIdEvenement() {
        System.out.print("Id de l'événement : ");
        return Long.parseLong(scanner.nextLine());
    }

    @Override
    public Long saisirIdBatiment() {
        System.out.print("Id du bâtiment : ");
        return Long.parseLong(scanner.nextLine());
    }

    @Override
    public SeigneurieDTO saisirSeigneurie() {
        String nom = LectureConsole.lectureChaineCaracteres("Nom de la seigneurie : ");
        String nomSeigneur = LectureConsole.lectureChaineCaracteres("Nom du seigneur : ");
        String prenomSeigneur = LectureConsole.lectureChaineCaracteres("Prénom du seigneur : ");
        LocalDate dateNaissance = LectureConsole.lectureLocalDate(
                "Date de naissance (jj/MM/aaaa) : ",
                "dd/MM/yyyy"
        );
        StatutHabitant statut = StatutHabitant.SEIGNEUR;
        return new SeigneurieDTO( nom, nomSeigneur, prenomSeigneur, dateNaissance, statut);
    }

    @Override
    public BatimentDTO saisirBatiment() {

        String nom = LectureConsole.lectureChaineCaracteres("Nom du bâtiment : ");

        boolean actif = LectureConsole.lectureBoolean("Le bâtiment est actif");

        Map<Integer, String> menu = new LinkedHashMap<>();

        int i = 1;
        for (TypeBat type : TypeBat.values()) {
            menu.put(i++, type.name());
        }

        AffichageConsole.afficherMenuEntoure(menu, "TYPE DE BÂTIMENT");
        int choix = LectureConsole.lectureChoixInt(1, TypeBat.values().length);

        TypeBat type = TypeBat.values()[choix - 1];

        return new BatimentDTO(
                nom,
                actif,
                type
        );
    }

    @Override
    public Habitant[] saisirHabitants() {

        System.out.print("Nombre d'habitants à migrer : ");
        int nb = Integer.parseInt(scanner.nextLine());

        Habitant[] habitants = new Habitant[nb];

        for (int i = 0; i < nb; i++) {

            System.out.println("L'habitant doit être récupéré depuis la base.");
            System.out.println("Cette méthode sera complétée dans la suite du projet.");

            habitants[i] = null;
        }

        return habitants;
    }

    @Override
    public Map<Ressource, Integer> saisirRessources() {

        System.out.println("Cette méthode sera complétée lorsque les ressources seront gérées.");

        return new HashMap<>();
    }

    @Override
    public EvenementDTO saisirEvenement() {

        String nom = LectureConsole.lectureChaineCaracteres("Nom de l'évènement : ");

        String description = LectureConsole.lectureChaineCaracteres("Description : ");

        LocalDateTime dateDebut = LectureConsole.lectureLocalDate(
                "Date de début (jj/MM/aaaa) : ",
                "dd/MM/yyyy"
        ).atStartOfDay();

        LocalDateTime dateFin = LectureConsole.lectureLocalDate(
                "Date de fin (jj/MM/aaaa) : ",
                "dd/MM/yyyy"
        ).atStartOfDay();

        Map<Integer, String> menu = new LinkedHashMap<>();

        int i = 1;
        for (TypeEvenement type : TypeEvenement.values()) {
            menu.put(i++, type.name());
        }

        AffichageConsole.afficherMenuEntoure(menu, "TYPE D'ÉVÈNEMENT");

        int choix = LectureConsole.lectureChoixInt(1, TypeEvenement.values().length);

        TypeEvenement type = TypeEvenement.values()[choix - 1];

        return new EvenementDTO(
                nom,
                description,
                dateDebut,
                dateFin,
                type
        );
    }

}