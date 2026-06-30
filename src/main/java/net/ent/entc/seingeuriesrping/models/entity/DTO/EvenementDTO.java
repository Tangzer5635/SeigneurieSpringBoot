package net.ent.entc.seingeuriesrping.models.entity.DTO;

import net.ent.entc.seingeuriesrping.models.referencies.TypeEvenement;

import java.time.LocalDateTime;

public record EvenementDTO(
        String nomEvenement,
        String descriptionEvenement,
        LocalDateTime dateDebut,
        LocalDateTime dateFin,
        TypeEvenement typeEvenement
) {
}