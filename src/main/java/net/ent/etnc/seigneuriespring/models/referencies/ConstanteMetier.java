package net.ent.etnc.seigneuriespring.models.referencies;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etnc.seigneuriespring.models.entity.vobjects.Nom;
import net.ent.etnc.seigneuriespring.models.entity.vobjects.Prenom;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConstanteMetier {

    public static final int NB_HABITANT_MINI = 2;

    public static final Nom[] NOM_HABITANT_POSSIBLE = {
            new Nom("MARTIN"),
            new Nom("DUPONT"),
            new Nom("DUPIED"),
            new Nom("HENRI"),
            new Nom("PETIT"),
            new Nom("GRAND"),
            new Nom("BERNARD"),
            new Nom("STEFEL"),
            new Nom("BLANC"),
            new Nom("PASQUIER")
    };

    public static final Prenom[] PRENOM_HABITANT_POSSIBLE = {
            new Prenom("PIERRE"),
            new Prenom("ALAIN"),
            new Prenom("MARTIN"),
            new Prenom("LOUIS"),
            new Prenom("PAUL"),
            new Prenom("ANTOINE"),
            new Prenom("LUC"),
            new Prenom("GABRIEL"),
            new Prenom("MARIE"),
            new Prenom("VIRGINIE"),
            new Prenom("JEANNE")
    };
}