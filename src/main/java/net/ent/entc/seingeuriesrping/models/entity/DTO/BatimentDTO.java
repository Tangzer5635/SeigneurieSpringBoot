package net.ent.entc.seingeuriesrping.models.entity.DTO;

import net.ent.entc.seingeuriesrping.models.referencies.TypeBat;

public record BatimentDTO(
        String nomBatiment,
        boolean estActif,
        TypeBat typeBatiment
) {
}