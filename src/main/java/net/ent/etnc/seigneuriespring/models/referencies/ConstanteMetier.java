package net.ent.etnc.seigneuriespring.models.referencies;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConstanteMetier {


    public static final int NB_HABITANT_MINI = 2;
    public static final String[] NOM_HABITANT_POSSIBLE = {"MARTIN", "DUPONT", "DUPIED", "HENRI", "PETIT", "GRAND", "BERNARD", "STEFEL", "BLANC", "PASQUIER"};

    public static final String[] PRENOM_HABITANT_POSSIBLE = {"PIERRE", "ALAIN", "MARTIN", "LOUIS", "PAUL", "ANTOINE", "LUC", "GABRIEL", "MARIE", "VIRGINIE", "JEANNE"};
}
