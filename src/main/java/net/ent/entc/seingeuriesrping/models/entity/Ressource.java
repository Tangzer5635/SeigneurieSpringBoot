package net.ent.entc.seingeuriesrping.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import net.ent.entc.seingeuriesrping.models.referencies.TypeRessource;

import java.io.Serializable;

@Entity
@Table(name = "ressource", uniqueConstraints = @UniqueConstraint(name = "uk___ressource___nom", columnNames = {"nomRessource"}))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = "nomRessource")
@ToString(callSuper = true, of = {"nomRessource", "typeRessource"})
public class Ressource extends AbstractEntity implements Serializable {

    @Getter
    @Setter(AccessLevel.PROTECTED)
    @NotBlank(message = "Le nom ne doit pas être vide")
    @Size(max = 50, message = "Le nom ne doit pas dépasser 50 caractères")
    @Column(name = "nomRessource", nullable = false, length = 50)
    private String nomRessource;

    @Getter
    @Setter(AccessLevel.PROTECTED)
    @NotNull(message = "Le type de ressource ne peut pas être vide")
    @Enumerated(EnumType.STRING)
    @Column(name = "typeRessource", nullable = false, length = 50)
    private TypeRessource typeRessource;


    @Override
    public String displayable() {
        return "Ressource : " + nomRessource;
    }
}
