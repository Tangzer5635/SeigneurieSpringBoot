package net.ent.etnc.seigneuriespring.batchs.batiment;

import net.ent.etnc.seigneuriespring.models.referencies.TypeBat;

public record BatimentLineCSV(String nom, boolean est_actif, TypeBat typeBat) {
}
