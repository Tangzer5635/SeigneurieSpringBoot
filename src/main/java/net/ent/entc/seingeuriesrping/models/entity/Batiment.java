package net.ent.entc.seingeuriesrping.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import net.ent.entc.seingeuriesrping.models.referencies.TypeBat;

import java.io.Serializable;

@Entity
@Table(name = "batiment",uniqueConstraints = @UniqueConstraint(name = "uk___batiment___nom", columnNames = {"nomBatiment"}))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = "nomBatiment")
@ToString(callSuper = true, of = {"nomBatiment", "estActif", "typeBatiment"})
public class Batiment extends AbstractEntity implements Serializable {

    @Getter
    @Setter(AccessLevel.PROTECTED)
    @NotBlank(message = "Le nom ne doit pas être vide")
    @Size(max = 50, message = "Le nom ne doit pas dépasser 50 caractères")
    @Column(name = "nomBatiment", nullable = false, length = 50)
    private String nomBatiment;

    @Getter
    @Setter(AccessLevel.PROTECTED)
    @NotNull(message = "Le bâtiment doit avoir un choix actif ou non")
    @Column(name = "est_actif", nullable = false)
    private Boolean estActif;

    @Getter
    @Setter(AccessLevel.PROTECTED)
    @NotNull(message = "Le bâtiment doit avoir un type")
    @Enumerated(EnumType.STRING)
    @Column(name = "typeBatiment", nullable = false, length = 50)
    private TypeBat typeBatiment;


    @Override
    public String displayable() {
        return "Bâtiment : " + nomBatiment;
    }
}