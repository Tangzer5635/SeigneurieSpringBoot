package net.ent.etnc.seigneuriespring.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import net.ent.etnc.seigneuriespring.models.entity.vobjects.Nom;
import net.ent.etnc.seigneuriespring.models.entity.vobjects.Prenom;
import net.ent.etnc.seigneuriespring.models.referencies.StatutHabitant;

import java.time.LocalDate;

@Entity
@Table(name = "habitant", uniqueConstraints = @UniqueConstraint(columnNames = {"nom", "prenom", "date_naissance"}, name = "uk___habitant___nom__prenom__date_naissance"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = {"nom", "prenom", "dateNaissance"})
@ToString(callSuper = true, of = {"nom", "prenom", "dateNaissance", "statut"})
public class Habitant extends AbstractEntity {

    @Getter
    @Setter(AccessLevel.PROTECTED)
    @Embedded
    private Nom nom;

    @Getter
    @Setter(AccessLevel.PROTECTED)
    @Embedded
    private Prenom prenom;

    @Getter
    @Setter(AccessLevel.PROTECTED)
    @NotNull(message = "La date de naissance ne peut pas etre vide")
    @PastOrPresent(message = "La date de naissance doit être dans le passer")
    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    @Getter
    @Setter(AccessLevel.PROTECTED)
    @NotNull(message = "Le statut ne peut pas etre vide")
    @Enumerated(value = EnumType.STRING)
    @Column(name = "statue", nullable = false, length = 15)
    private StatutHabitant statut;
}
