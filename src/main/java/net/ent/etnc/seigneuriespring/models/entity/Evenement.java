package net.ent.etnc.seigneuriespring.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import net.ent.etnc.seigneuriespring.models.entity.vobjects.Nom;
import net.ent.etnc.seigneuriespring.models.referencies.TypeEvenement;

import java.time.LocalDateTime;

@Entity
@Table(name = "evenement", uniqueConstraints = @UniqueConstraint(columnNames = {"nom", "date_debut"}))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = {"nom", "dateDebut"})
@ToString(callSuper = true, of = {"nom", "description", "dateDebut", "dateFin", "type"})
public class Evenement extends AbstractEntity {
    @Getter
    @Setter(AccessLevel.PROTECTED)
    @Embedded
    private Nom nom;
    //LBK
    @Getter
    @Setter
    //BV
    @NotBlank(message = "La description de doit pas être vide")
    @Column(name = "description", nullable = false, length = 255)
    private String description;
    //LBK
    @Getter
    @Setter(AccessLevel.PROTECTED)
    //BV
    @NotNull(message = "La date de début ne doit pas être vide")
    //JPA
    @Column(name = "date_debut", nullable = false)
    private LocalDateTime dateDebut;
    //LBK
    @Getter
    @Setter
    //JPA
    @Column(name = "date_fin", nullable = true)
    private LocalDateTime dateFin;
    //LBK
    @Getter
    @Setter(AccessLevel.PROTECTED)
    //BV
    @NotNull(message = "L'évènement doit avoir un type")
    //JPA
    @Enumerated(value = EnumType.STRING)
    @Column(name = "type_batiment", nullable = false, length = 20)
    private TypeEvenement type;

}
