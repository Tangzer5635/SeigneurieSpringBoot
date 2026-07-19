package net.ent.etnc.seigneuriespring.shell;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.ent.etnc.seigneuriespring.models.facade.SeigneurieFacade;
import net.ent.etnc.seigneuriespring.models.facade.exception.FacadeMetierException;
import net.ent.etnc.seigneuriespring.shell.exceptions.ShellException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

@ShellComponent
@RequiredArgsConstructor
public class SeigneurieCommands {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.FRENCH);


    @NonNull
    private final SeigneurieFacade seigneurieFacade;

    @ShellMethod(key = "s-list", value = "Affiche toutes les seigneuries")
    public void afficherSeigneuries() {
        try {
            this.seigneurieFacade.recupererToutesLesSeigneuries()
                    .forEach(seigneurie -> {
                        System.out.printf("""
                    ┌────────────────────────────────────────────
                    │ Nom        : %s
                    │ Seigneur   : %s %s
                    │ Né le      : %s
                    └────────────────────────────────────────────
                    
                    """,
                                seigneurie.getNom(),
                                seigneurie.getSeigneur().getNom().nom(),
                                seigneurie.getSeigneur().getPrenom().prenom(),
                                seigneurie.getSeigneur().getDateNaissance().format(formatter)
                        );
                    });
        } catch (FacadeMetierException e) {
            throw new ShellException(e.getMessage());
        }
    }

    @ShellMethod(key = "s-renew", value = "Permet de déclencher le renouvellement de la population d'une seingeurie")
    public void renouvelerPopulationSeigneurie(
            @ShellOption(value = {"-i", "--idSeigneurie"}, help = "l'identifiant de la seigneurie") Long idSeigneurie
    ) {
        try {
            this.seigneurieFacade.renouvelerPopulationSeigneurie(idSeigneurie);
        } catch (FacadeMetierException e) {
            throw new ShellException(e.getMessage());
        }
    }

    
}
