package net.ent.entc.seingeuriesrping.models.entity.DTO;

import java.time.LocalDate;
import net.ent.entc.seingeuriesrping.models.referencies.StatutHabitant;

public record SeigneurieDTO(
        String nom,
        String nomSeigneur,
        String prenomSeigneur,
        LocalDate dateNaissanceSeigneur,
        StatutHabitant statutSeigneur
) {}