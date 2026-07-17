package net.ent.etnc.seigneuriespring.batchs.habitant;

import net.ent.etnc.seigneuriespring.models.referencies.StatutHabitant;

import java.time.LocalDate;

public record HabitantLineCSV(String nom, String prenom, LocalDate date_naissance, StatutHabitant statue) {
}
