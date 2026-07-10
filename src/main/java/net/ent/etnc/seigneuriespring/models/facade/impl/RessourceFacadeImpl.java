package net.ent.etnc.seigneuriespring.models.facade.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.ent.etnc.seigneuriespring.models.entity.Ressource;
import net.ent.etnc.seigneuriespring.models.entity.Seigneurie;
import net.ent.etnc.seigneuriespring.models.facade.RessourceFacade;
import net.ent.etnc.seigneuriespring.models.facade.exception.FacadeMetierException;
import net.ent.etnc.seigneuriespring.models.services.commun.exceptions.ServiceException;
import net.ent.etnc.seigneuriespring.models.services.crud.RessourceService;
import net.ent.etnc.seigneuriespring.models.services.crud.SeigneurieService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RessourceFacadeImpl implements RessourceFacade {

    @NonNull
    private final SeigneurieService seigneurieService;

    @NonNull
    private final RessourceService ressourceService;

    @Override
    public void ajouterRessourcesSeigneurie(Long[][] ressources, Long idSeigneurie) throws FacadeMetierException, ServiceException {
        Seigneurie seigneurie = seigneurieService.findById(idSeigneurie);

        for (Long[] ressource : ressources) {
            Ressource res = ressourceService.findById(ressource[0]);
            try {
                seigneurie.addRessource(res, Math.toIntExact(ressource[1]));
            } catch (ArithmeticException e) {
                throw new FacadeMetierException("Erreur de conversion quantité ressource", e);
            }
        }
        seigneurieService.save(seigneurie);
    }

    @Override
    public void echangeRessourcesEntreSeigneurie(Long idSeigneurieEmettrice, Long idSeigneurieReceptrice, Long[][] ressources) throws FacadeMetierException, ServiceException {
        Seigneurie seigneurieEmettrice = seigneurieService.findById(idSeigneurieEmettrice);
        Seigneurie seigneurieReceptrice = seigneurieService.findById(idSeigneurieReceptrice);

        for (Long[] ressourceData : ressources) {
            Long idRessource = ressourceData[0];
            int quantite = Math.toIntExact(ressourceData[1]);

            Ressource r = ressourceService.findById(idRessource);

            try {
                Integer quantitePossedee = seigneurieEmettrice.getRessources().get(r);
                if (quantitePossedee == null || quantitePossedee < quantite) {
                    throw new FacadeMetierException("La ressource n'est pas assez disponible");
                }

                seigneurieEmettrice.removeRessource(r, quantite);
                seigneurieReceptrice.addRessource(r, quantite);

            } catch (ArithmeticException e) {
                throw new FacadeMetierException("Erreur conversion quantité ressource", e);
            }
        }

        seigneurieService.save(seigneurieEmettrice);
        seigneurieService.save(seigneurieReceptrice);
    }
}
