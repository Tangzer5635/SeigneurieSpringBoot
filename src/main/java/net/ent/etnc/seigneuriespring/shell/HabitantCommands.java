package net.ent.etnc.seigneuriespring.shell;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.ent.etnc.seigneuriespring.models.facade.HabitantFacade;
import net.ent.etnc.seigneuriespring.models.facade.exception.FacadeMetierException;
import net.ent.etnc.seigneuriespring.shell.exceptions.ShellException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class HabitantCommands {

    @NonNull
    private final HabitantFacade habitantFacade;

    @ShellMethod(key = "h-list", value = "Affiche tous les habitants")
    public void afficherHabitants() {
        try {
            habitantFacade.recupererLesHabitants()
                    .forEach(System.out::println);
        } catch (FacadeMetierException e) {
            throw new ShellException(e.getMessage());
        }
    }
}