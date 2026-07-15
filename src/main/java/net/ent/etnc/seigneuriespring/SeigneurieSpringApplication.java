package net.ent.etnc.seigneuriespring;

import net.ent.etnc.seigneuriespring.models.facade.InitFacade;
import net.ent.etnc.seigneuriespring.models.facade.exception.FacadeMetierException;
import net.ent.etnc.seigneuriespring.models.facade.impl.InitFacadeImpl;
import net.ent.etnc.seigneuriespring.models.services.crud.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeigneurieSpringApplication {

    private static InitFacade initFacade;

    public SeigneurieSpringApplication(
            SeigneurieService seigneurieService,
            RessourceService ressourceService,
            BatimentService batimentService,
            EvenementService evenementService,
            HabitantService habitantService,
            SeigneurieEnvService seigneurieEnvService) {

        initFacade = new InitFacadeImpl(
                habitantService,
                ressourceService,
                evenementService,
                seigneurieService,
                batimentService,
                seigneurieEnvService
        );
    }

    public static void main(String[] args) throws FacadeMetierException {
        SpringApplication.run(SeigneurieSpringApplication.class, args);
        initFacade.initialiserDonnees();
    }
}