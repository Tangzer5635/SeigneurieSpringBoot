package net.ent.entc.seingeuriesrping.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;
import net.ent.entc.seingeuriesrping.models.referencies.StatutHabitant;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "habitant", uniqueConstraints = @UniqueConstraint(name = "uk___habitant___nom__prenom", columnNames = {"nomHabitant", "prenomHabitant"}))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = {"nomHabitant", "prenomHabitant", "dateNaissance"})
@ToString(callSuper = true, of = {"nomHabitant", "prenomHabitant", "dateNaissance", "statutHabitant"})
public class Habitant extends AbstractEntity{

    @Getter
    @Setter(AccessLevel.PROTECTED)
    @NotBlank(message = "Le nom ne doit pas être vide")
    @Size(max = 50, message = "Le nom ne doit pas dépasser 50 caractères")
    @Column(name = "nomHabitant", nullable = false, length = 50)
    private String nomHabitant;

    @Getter
    @Setter(AccessLevel.PROTECTED)
    @NotBlank(message = "Le prénom ne doit pas être vide")
    @Size(max = 50, message = "Le prénom ne doit pas dépasser 50 caractères")
    @Column(name = "prenomHabitant", nullable = false, length = 50)
    private String prenomHabitant;

    @Getter
    @Setter(AccessLevel.PROTECTED)
    @NotNull(message = "La date de naissance ne peut pas être vide")
    @PastOrPresent(message = "La date de naissance doit être dans le passé")
    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    @Getter
    @Setter(AccessLevel.PROTECTED)
    @NotNull(message = "Le statut ne peut pas être vide")
    @Enumerated(EnumType.STRING)
    @Column(name = "statutHabitant", nullable = false, length = 50)
    private StatutHabitant statutHabitant;

}
