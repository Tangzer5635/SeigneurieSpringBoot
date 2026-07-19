package net.ent.etnc.seigneuriespring.shell;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.ent.etnc.seigneuriespring.models.facade.HabitantFacade;
import net.ent.etnc.seigneuriespring.models.facade.exception.FacadeMetierException;
import net.ent.etnc.seigneuriespring.shell.exceptions.ShellException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

@ShellComponent
@RequiredArgsConstructor
public class HabitantCommands {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.FRENCH);


    @NonNull
    private final HabitantFacade habitantFacade;

    @ShellMethod(key = "h-list", value = "Affiche tous les habitants")
    public void afficherHabitants() {
        try {
            habitantFacade.recupererLesHabitants()
                    .forEach(habitant -> {
                        System.out.printf("""
                                        ┌──────────────────────────────
                                        │ Nom     : %s %s
                                        │ Né le   : %s
                                        │ Statut  : %s
                                        └──────────────────────────────
                                        
                                        """,
                                habitant.getNom().nom(),
                                habitant.getPrenom().prenom(),
                                habitant.getDateNaissance().format(formatter),
                                habitant.getStatut()
                        );
                    });
        } catch (FacadeMetierException e) {
            throw new ShellException(e.getMessage());
        }
    }
}