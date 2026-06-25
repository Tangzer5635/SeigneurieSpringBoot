package net.ent.entc.seingeuriesrping.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import net.ent.entc.seingeuriesrping.models.referencies.TypeEvenement;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "evenement", uniqueConstraints = @UniqueConstraint(name = "uk___evenement___nom", columnNames = {"nomEvenement"}))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = {"nomEvenement", "dateDebutEvenement"})
@ToString(callSuper = true, of = {"nomEvenement", "descriptionEvenement", "dateDebut", "dateFin", "typeEvenement"})
public class Evenement extends AbstractEntity implements Serializable {

    @Getter
    @Setter(AccessLevel.PROTECTED)
    @NotBlank(message = "Le nom ne doit pas être vide")
    @Size(max = 50, message = "Le nom ne doit pas dépasser 50 caractères")
    @Column(name = "nomEvenement", nullable = false, length = 50)
    private String nomEvenement;

    @Getter
    @Setter(AccessLevel.PROTECTED)
    @NotBlank(message = "La description ne doit pas être vide")
    @Size(max = 200, message = "La description ne doit pas dépasser 200 caractères")
    @Column(name = "descriptionEvenement", nullable = false, length = 200)
    private String descriptionEvenement;

    @Getter
    @Setter(AccessLevel.PROTECTED)
    @NotNull(message = "La date de début ne doit pas être vide")
    @Column(name = "date_debut", nullable = false)
    private LocalDateTime dateDebut;

    @Getter
    @Setter
    @NotNull(message = "La date de fin ne doit pas être vide")
    @Column(name = "date_fin", nullable = false)
    private LocalDateTime dateFin;

    @Getter
    @Setter(AccessLevel.PROTECTED)
    @NotNull(message = "L'événement doit avoir un type")
    @Enumerated(EnumType.STRING)
    @Column(name = "typeEvenement", nullable = false, length = 50)
    private TypeEvenement typeEvenement;



    @Override
    public String displayable() {
        return "Événement : " + nomEvenement;
    }
}
