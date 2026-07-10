package net.ent.etnc.seigneuriespring;

import net.ent.etnc.seigneuriespring.models.facade.exception.FacadeMetierException;
import net.ent.etnc.seigneuriespring.models.repositories.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeigneurieSpringApplication {

    private static SeigneurieRepository seigneurieRepository;
    private static RessourceRepository ressourceRepository;
    private static BatimentRepository batimentRepository;
    private static EvenementRepository evenementRepository;
    private static HabitantRepository habitantRepository;

    public SeigneurieSpringApplication(SeigneurieRepository seigneurieRepository
            , RessourceRepository ressourceRepository
            , BatimentRepository batimentRepository
            , EvenementRepository evenementRepository
            , HabitantRepository habitantRepository) {
        SeigneurieSpringApplication.seigneurieRepository = seigneurieRepository;
        SeigneurieSpringApplication.ressourceRepository = ressourceRepository;
        SeigneurieSpringApplication.batimentRepository = batimentRepository;
        SeigneurieSpringApplication.evenementRepository = evenementRepository;
        SeigneurieSpringApplication.habitantRepository = habitantRepository;
    }

    public static void main(String[] args) throws FacadeMetierException {
        SpringApplication.run(SeigneurieSpringApplication.class, args);

        FacadeMetier facadeMetier = new FacadeMetierImpl(seigneurieRepository, habitantRepository, ressourceRepository, evenementRepository, batimentRepository);

//        facadeMetier.creerSeigneurie(new SeigneurieDTO("Mon Domaine", 1L));
        facadeMetier.initialisation();


    }

}
