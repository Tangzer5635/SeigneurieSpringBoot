package net.ent.etnc.seigneuriespring.batchs.evenement;

import net.ent.etnc.seigneuriespring.models.referencies.TypeEvenement;

import java.time.LocalDateTime;

public record EvenementLineCSV(String nom, String description, LocalDateTime date_debut, LocalDateTime date_fin,
                               TypeEvenement type) {
}