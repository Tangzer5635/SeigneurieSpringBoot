package net.ent.etnc.seigneuriespring.shell;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.ent.etnc.seigneuriespring.models.facade.InitFacade;
import net.ent.etnc.seigneuriespring.models.facade.exception.FacadeMetierException;
import org.jline.terminal.Terminal;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import static java.awt.Color.red;
import static java.awt.Color.white;

@ShellComponent
@RequiredArgsConstructor
public class ChargementCommands {

    @NonNull
    private final Terminal terminal;
    @NonNull
    private final InitFacade initFacade;

    @ShellMethod(key = "init", value = "lancement de l'initialisation")
    public void init() {
        try {
            initFacade.initialiserDonnees();
        } catch (FacadeMetierException e) {
            terminal.writer().println(red + e.getMessage() + white);
        }
    }
}