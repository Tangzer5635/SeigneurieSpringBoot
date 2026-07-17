package net.ent.etnc.seigneuriespring;

import net.ent.etnc.seigneuriespring.models.facade.exception.FacadeMetierException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeigneurieSpringApplication {

    public static void main(String[] args) throws FacadeMetierException {
        SpringApplication.run(SeigneurieSpringApplication.class, args);
    }
}