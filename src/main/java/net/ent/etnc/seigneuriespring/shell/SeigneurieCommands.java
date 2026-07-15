package net.ent.etnc.seigneuriespring.shell;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.ent.etnc.seigneuriespring.models.facade.SeigneurieFacade;
import net.ent.etnc.seigneuriespring.models.facade.exception.FacadeMetierException;
import net.ent.etnc.seigneuriespring.shell.exceptions.ShellException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class SeigneurieCommands {
    @NonNull
    private final SeigneurieFacade seigneurieFacade;

    @ShellMethod(key = "s-list", value = "Affiche toutes les seigneuries")
    public void afficherSeigneuries() {
        try {
            this.seigneurieFacade.recupererToutesLesSeigneuries()
                    .forEach(System.out::println);
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
